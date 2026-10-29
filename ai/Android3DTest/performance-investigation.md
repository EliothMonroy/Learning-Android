# Distrito performance investigation — 2026-10-28

The original diagnosis below describes the pre-fix app. Its source line numbers refer to that earlier implementation. See **Post-fix results** for the implementation and fresh measurements after the user authorized the fixes.

## Finding

The primary bottleneck is excessive antialiased Canvas path processing on RenderThread. The custom 3D renderer submits thousands of individually filled and stroked polygons. During rotation the UI thread waits for RenderThread to finish this work.

No application source code was changed during this investigation.

## Reproduction and scope

- Existing debug APK, package `xyz.eliothmonroy.a3dtest`.
- Connected emulator `emulator-5554`, Android 17, 1080 × 2400, Skia OpenGL.
- Open Palacio de Bellas Artes, then drag the model horizontally.
- Recorded a 45-second Perfetto system trace covering detail opening and rotation.
- Trace: [/private/tmp/distrito-lag.perfetto-trace](/private/tmp/distrito-lag.perfetto-trace).
- Exact timings are emulator/debug measurements; physical-device release performance has not been measured.

## Measured evidence

[DEVICE] Before profiling, gfxinfo reported 95/95 frames janky, with a histogram median around 600 ms.

[DEVICE] After resetting gfxinfo and performing another rotation gesture, 9/9 frames were janky, with a histogram median around 700 ms. The recorded frame interval was 16,666,666 ns, or about 16.7 ms.

[SQL] A recurring rotation window, 1,957,637,084,224–1,959,703,682,808 ns (approximately 16.266–18.332 seconds into the trace), shows:

| Thread | CPU running time in this 2.067-second window |
| --- | ---: |
| Main/UI thread | 118.48 ms |
| RenderThread | 1,999.02 ms |

[SQL] The UI thread slept for 1,837.73 ms in that window. RenderThread spent approximately 97% of the window running, with only 15.56 ms runnable but unscheduled. These totals count wholly contained scheduling intervals and omit boundary-straddling intervals. Exact intersections for the representative frame show RenderThread flush spending 326.072 of 327.812 ms running, AAConvexPathOp spending 254.763 of 256.089 ms running, and the UI postAndWait spending 317.161 of 317.218 ms sleeping. This establishes CPU path preparation as the dominant stall rather than scheduler starvation or a GPU fence wait.

[SQL] One representative frame has a UI `Record View#draw` of 18.899 ms followed by `postAndWait` of 317.218 ms. RenderThread has `flush` of 327.812 ms, `onPrepare` of 307.400 ms, and `AAConvexPathOp` of 256.089 ms. Repeated frames show the same pattern: UI drawing around 18–23 ms, waiting around 260–274 ms, and RenderThread flush around 274–288 ms.

[SQL] Across the complete trace, there are 108,650 `AAConvexPathOp` slices totaling 3,462.9 ms, and 415,012 `AAHairlineOp` slices totaling 507.9 ms. RenderThread `flush` slices total 4,250.1 ms. These nested timings must not be added together.

[GAP] Four frame-timeline parser errors occurred. The diagnosis therefore relies on thread scheduling, rendering slices, and independent gfxinfo measurements rather than treating frame-timeline classifications as complete. The saturated gfxinfo GPU histogram is not used to claim a specific GPU execution duration.

## Source-level explanation

[CODE] original `LandmarkView.java:159` allocates a new transformed face list on every draw, creates arrays for each face and vertex, and sorts all faces.

[CODE] original `LandmarkView.java:168` creates a new Path for every face. Lines 174–175 issue a fill and an antialiased stroke for every polygon, including hidden faces.

[CODE] The Bellas Artes construction produces 8,424 faces, hence 16,848 `drawPath` calls per repaint. This is calculated from the geometry-building loops, not measured with an allocation profiler. Four decorative trees alone contribute 2,384 faces. Each cylinder adds 20 side faces plus a 192-face curved cap, even for very small architectural details.

[INFERRED] This unusually large path workload explains the trace's repeated antialiased convex-path and hairline preparation costs. The UI thread's additional projection, sorting, and allocation work is real, but optimizing Java allocation alone would leave the main RenderThread bottleneck.

[CODE] original `MainActivity.java:95` removes and recreates the screen on tab changes, filters, and bookmark taps. New LandmarkViews rebuild their meshes synchronously in their constructors. Even noninteractive thumbnail views use the full geometry.

[INFERRED] Full-screen rebuilds and uncached full-detail thumbnails add interaction and navigation cost. This investigation does not assign a measured percentage to those secondary costs.

## Recommended fix order

1. Reduce the renderer's graphics workload: remove the per-polygon hairline pass, simplify tiny cylinder caps and trees, avoid submitting hidden surfaces, and batch triangle geometry instead of issuing a separate Path operation for each face.
2. Render noninteractive previews once and cache them at the required size. Share immutable mesh data instead of rebuilding it for every preview.
3. Reuse projection arrays and drawing buffers; compute trigonometric camera values once per frame.
4. Update bookmark controls and changed content in place instead of rebuilding the complete screen.
5. Repeat the same rotation capture and frame measurements, then validate on a physical device with a non-debuggable build.

Android's official slow-rendering guidance documents expensive path rendering, RenderThread stalls, and allocation costs: https://developer.android.com/topic/performance/issues/render

## Post-fix results

The authorized fixes were initially implemented in Java. The current implementation is in [LandmarkView.kt](app/src/main/java/xyz/eliothmonroy/a3dtest/LandmarkView.kt) and [MainActivity.kt](app/src/main/java/xyz/eliothmonroy/a3dtest/MainActivity.kt). Historical Java file names and line numbers below describe the earlier measurements.

- A single colored triangle batch replaces per-face antialiased fills and hairline strokes.
- Projected vertices, colors, depths, and sort storage are reused. Camera trigonometry is calculated once per update; mesh normals are precomputed.
- Immutable geometry is shared across views for each of the six landmarks. Small cylinder caps and domes use simpler geometry.
- Noninteractive previews use a size-specific Bitmap cache capped at 12 MiB, with raster dimensions capped at 768 pixels on the longer side. Views may retain their displayed image after cache eviction; eviction never recycles an image that a view still owns.
- Bookmarks update in place. Category changes replace only the results area. Up to three tab pages retain their models and scroll positions.
- The collection opens in a bottom sheet, retaining the discovery screen behind it.

### Same-emulator rotation measurement

Existing debug build configuration, same emulator and model; a horizontal swipe from (800,1000) to (320,1000), lasting 1,500 ms. gfxinfo was reset before each sample.

| Metric | Original sample | Fixed sample |
| --- | ---: | ---: |
| Recorded frames | 9 | 47 |
| Histogram median frame time | ~700 ms | ~22 ms |
| Fixed sample exact median, from frame timestamps | — | 22.133 ms |
| Histogram p90 | ~750 ms | ~38 ms |
| Histogram p95 | ~750 ms | ~40 ms |
| Janky frames | 9/9 | 44/47 |

[DEVICE] The fixed sample has a median UI draw interval of 0.433 ms, median render sync queue delay of 0.109 ms, and median issue-draw-commands interval of 2.247 ms. The largest frame duration in this sample is 50.445 ms.

[INFERRED] The median frame time fell by approximately 32 times for this reproduction. The samples contain different numbers of frames because the fixed renderer processes substantially more updates during the gesture; this is a practical reproduction comparison, not a statistically controlled device benchmark.

[GAP] The fixed emulator sample still misses its 16.7 ms deadline on 44 of 47 frames. These results establish removal of the original large stall, not sustained 60 fps. A physical device and non-debuggable build remain to be profiled.

The timestamp-derived fixed metrics are saved in [docs/rotation-after.json](docs/rotation-after.json).

### Follow-up Perfetto evidence

Trace: [/private/tmp/distrito-fixed.perfetto-trace](/private/tmp/distrito-fixed.perfetto-trace), 25 seconds, including another rotation gesture.

[SQL] RenderThread has 50 flush commands totaling 267.705 ms, averaging 5.354 ms, with a maximum of 28.649 ms. There are only 100 AAConvexPathOp slices totaling 0.462 ms, with a maximum of 0.021 ms, and no AAHairlineOp rows for RenderThread. The previous hundreds-of-milliseconds convex-path preparation is no longer the dominant workload. These are trace-specific totals; the original and fixed recordings have different durations and frame counts, so totals should not be compared as rates.

Validated query:

```sql
INCLUDE PERFETTO MODULE slices.with_context;
SELECT s.thread_name,s.name,COUNT(*) n,
       ROUND(SUM(s.dur)/1e6,3) total_ms,
       ROUND(MAX(s.dur)/1e6,3) max_ms
FROM thread_slice s
WHERE s.process_name='xyz.eliothmonroy.a3dtest'
  AND s.thread_name='RenderThread' AND s.dur>0
  AND (s.name GLOB '*flush*' OR s.name GLOB '*AAConvexPathOp*'
       OR s.name GLOB '*AAHairlineOp*' OR s.name GLOB '*DrawVertices*')
GROUP BY s.thread_name,s.name
ORDER BY total_ms DESC;
```

### Verification and review

- The three new performance regression tests were first run against the original app and failed for the diagnosed reasons.
- Full verification passed: assembleDebug, testDebugUnitTest, lintDebug, and connectedDebugAndroidTest.
- Device suite: 7 tests passed, zero failures. Six regression tests cover one-batch drawing, preview reuse, in-place bookmarks, rotation/reset output for all six models, cache separation/resizing, and retained tab models/scroll. The seventh is the existing package instrumentation test.
- The existing JVM test passed.
- A read-only independent review found no Important/Critical defects.
- Repeated-reset pixel comparisons pass for all six models. The review's theoretical equal-depth sort instability did not reproduce a visible defect in these checks.
- Scroll restoration across Activity recreation remains outside this fix; tab switching is covered.
- Final manual checks include home and detail appearance, rotation, category filtering, saved collection, city map, and collection sheet.

The batched renderer retains painter ordering for the complete simplified mesh. Backface culling was deferred because the original geometry has inconsistent face windings; speculative culling could remove valid surfaces. Vertex batching already reduces median graphics-command issuing to about 2.25 ms in the measured sample.

## Kotlin migration verification

The four production classes and the rendering regression test have been migrated to Kotlin, retaining Android Views, the same package/class names, attraction data and optimized triangle batching. No Java source files remain under `app/src`. The existing AGP built-in Kotlin support was sufficient; its JVM target follows the existing Java 11 targetCompatibility.

[DEVICE] A fresh debug-emulator sample on emulator-5554 used the same Bellas Artes horizontal rotation gesture from (800,1000) to (320,1000), lasting 1,500 ms, after resetting gfxinfo:

| Metric | Kotlin sample |
| --- | ---: |
| Recorded frames | 49 |
| Janky frames | 43 / 49 |
| Exact median frame duration | 23.363 ms |
| Histogram median / p90 / p95 | 23 / 42 / 53 ms |
| Median UI drawing | 0.801 ms |
| Median graphics-command issuing | 1.297 ms |

The previously recorded optimized Java sample had a 22.133 ms exact median across 47 frames. It was captured earlier in the emulator session; this is a bounded historical comparison, not a controlled Java-versus-Kotlin performance claim. The original hundreds-of-milliseconds rendering stall was not observed in this Kotlin sample. Sustained 60 fps and physical-device release behavior remain unverified.

Metrics: [docs/rotation-kotlin.json](docs/rotation-kotlin.json).

Verification: assembleDebug, testDebugUnitTest, lintDebug, and connectedDebugAndroidTest pass. Eight instrumentation tests pass, including all six model rotation/reset checks, cached preview reuse/resizing, bookmarks, retained tabs, and a new synthetic two-pointer pinch/reset check. The existing JVM test also passes. The new gesture fixture explicitly sizes the view and respects the device's minimum scaling span.

A read-only comparison against the Java baselines found no Critical/Important migration defects. Home, interactive viewer and map appearance were checked on the running emulator. Existing README screenshots remain representative because no visible redesign was made.

## Recording command

```sh
python3 /private/tmp/distrito-record_android_trace \
  --serial emulator-5554 -t 45s -b 64mb \
  sched freq gfx view wm am dalvik \
  -a xyz.eliothmonroy.a3dtest -n \
  -o /private/tmp/distrito-lag.perfetto-trace
```

## Reproducible queries

Load the trace with the bundled processor:

```sh
python3 /Users/eliothmonroy/.android-studio/skills/android-profiler/references/perfetto/bin/trace_processor \
  server unix --name distrito_lag --daemonize /private/tmp/distrito-lag.perfetto-trace
```

Run each SQL statement with the same wrapper using `query --remote distrito_lag 'SQL'`. IDs below belong to this trace.

```sql
-- Main and RenderThread slice evidence.
SELECT t.utid,s.id,s.parent_id,s.ts,s.dur,
       ROUND(s.dur/1e6,3) ms,s.depth,s.name
FROM slice s
JOIN thread_track tt ON s.track_id=tt.id
JOIN thread t USING(utid)
WHERE t.utid IN(65,78)
  AND s.ts BETWEEN 1957637084224 AND 1959703682808
  AND s.dur>10000000
ORDER BY s.ts,s.depth
LIMIT 110;

-- Exact scheduling intersections for the representative frame.
INCLUDE PERFETTO MODULE slices.time_in_state;
SELECT s.id,s.utid,s.name,s.state,ROUND(s.dur/1e6,3) ms
FROM thread_slice_time_in_state s
WHERE s.id IN(401154,401187,401228)
ORDER BY s.id,s.dur DESC;

-- Whole-trace operation totals. Nested totals are not additive.
SELECT t.utid,s.name,COUNT(*) n,
       ROUND(SUM(s.dur)/1e6,1) total_ms,
       ROUND(MAX(s.dur)/1e6,1) max_ms
FROM slice s
JOIN thread_track tt ON s.track_id=tt.id
JOIN thread t USING(utid)
WHERE t.utid IN(65,78) AND s.dur>0
GROUP BY t.utid,s.name
ORDER BY total_ms DESC
LIMIT 35;
```

The trace does not contain Java method stack samples; matching the heavy rendering operation to the application's per-face drawing loop is a source-supported inference. That statement applies to the initial trace analysis; later optimized-renderer and Kotlin measurements are recorded in the sections above.
