# Performance fix plan

Approved scope: implement the fixes recommended in performance-investigation.md, retaining the current Material 3 design, all six offline models, orbit/zoom controls, filters, bookmarks, and map handoff.

Implementation approach: inline, using Android Studio tools. This workspace has no Git repository; changes remain local. The existing investigation report is retained and extended with measured results.

1. Add regression tests for the demonstrated failure: a model repaint must submit one triangle batch with no per-face Path operations; static previews must reuse cached raster output; bookmark actions must retain the current screen/model instance. Run against the original implementation and confirm failure.
2. Replace the per-face renderer with a reusable triangle buffer and one Canvas.drawVertices call. Calculate camera trigonometry once per frame, retain immutable meshes per model, precompute face normals, reuse projection and sorting storage, simplify small cylinder caps/domes, and cache bounded, size-specific static previews. Keep dynamic buffers per interactive view.
3. Update bookmark controls in place; retain a tab's screen until its content is invalidated. Preserve scroll positions when switching tabs. Re-run tests/build/lint, emulator interactions and the same rotation frame measurement, plus a Perfetto trace. Request one independent code review and record measured results.

Verification: instrumentation exercises actual Canvas output and Activity interactions; build and existing JVM tests; same-emulator debug frame data before/after; visual review of home, viewer and map.

Review focus: cached Bitmap ownership/lifetime, mutable buffers not shared across views, memory bounded across sizes/configurations, triangle winding/shading and correct model appearance, zoom/rotation invalidation, bookmarks reflected on retained tabs, and hidden tab views not growing without bound.

Progress:
- Plan established from approved diagnosis.
- Task 1 complete: all three regression tests failed against the original app for the expected reasons: 16,848 Path calls per draw; previews repeated them; bookmarking replaced the visible model.
- Task 2 complete: batched triangle rendering and cached previews pass those rendering regressions. Geometry/output buffers are per view; immutable source meshes are shared. Cylinder caps are flat convex polygons instead of tiny 192-face hemispheres.
- Task 3 implementation complete: bookmark controls update in place, category changes rebuild only the results, three tab pages retain views and scroll positions. All three original regression tests pass. Functional camera/reset, cache-resize and retained-tab tests added for final verification.
- Implementation decision: retain painter ordering and submit the complete simplified mesh in one batch. Existing face windings are inconsistent; adding backface culling could remove valid surfaces. The measured graphics-command median is already 2.25 ms, so culling is deferred.
- Review: no Critical/Important source issues. Repeated-reset pixel checks added to investigate a potential equal-depth sorting issue; scroll restoration across activity recreation remains outside this fix (tab switching is covered).
- Same-emulator rotation measured 47 frames, histogram median22 ms, p9038 ms, p9540 ms; original9-frame sample median700 ms. Missed deadlines remain44/47, so this is not a claim of sustained60 fps.
- Post-fix25-second Perfetto capture:50 RenderThread flushes total267.705 ms (average5.354 ms, maximum28.649 ms);100 AAConvexPathOp slices total0.462 ms, maximum0.021 ms. No AAHairlineOp rows for RenderThread. Original per-polygon preparation is no longer the dominant stall.
- Complete: assembleDebug, lintDebug, the existing JVM test, and all 7 instrumentation tests pass. All six models pass rotation/reset pixel checks; cached preview resize/model separation and tab scroll/model reuse pass. Independent review found no blocking defects. The report now records original and post-fix evidence separately. Final manual checks and launch completed.
