# Kotlin migration

User-approved scope: migrate all app source and tests to Kotlin while preserving Android Views, Material 3 visuals, offline data, navigation and bookmark behavior, and the optimized renderer.

1. Run the existing build, lint and tests as a baseline; preserve source snapshots during conversion.
2. Convert Attraction, Symbol, LandmarkView, MainActivity and RenderingRegressionTest to Kotlin. Keep primitive FloatArray/IntArray buffers and indexed loops in the renderer; do not introduce collection allocations inside drawing/projection.
3. Remove the five superseded Java files after their Kotlin replacements are written. Keep package/class names and resource IDs stable.
4. Use AGP's existing built-in Kotlin support, align Kotlin JVM output with Java 11, and update README/AGENTS source references to Kotlin.
5. Run build, lint, JVM and device tests; check home/viewer/map, rotation/reset/zoom, filters, saved state and retained tabs. Capture fresh rotation metrics on the same debug emulator. Keep earlier performance evidence historical and append migration results.
6. Obtain a read-only independent review and leave all migration changes local.

Progress: complete.

- Baseline: Java build, JVM test, lint and all seven existing instrumentation tests passed before conversion.
- Converted all four production classes and the Java regression test to Kotlin. Existing Kotlin sample tests remain intact. No Java sources remain under app/src; both compileDebugJavaWithJavac and compileDebugAndroidTestJavaWithJavac report NO-SOURCE.
- Preserved the original sources in /private/tmp/distrito-kotlin-baseline. Superseded Java files were retired from the app source tree. File-removal tool timeouts were resolved by moving those files into the temporary baseline directory.
- The existing AGP built-in Kotlin support already aligns JVM output with Java 11 targetCompatibility, so no plugin/dependency or Gradle version change was necessary.
- Kotlin verification passed: assembleDebug, testDebugUnitTest, lintDebug and connectedDebugAndroidTest. Eight device tests now pass, including new pinch/reset coverage; the existing JVM test passes.
- The new pinch test initially exposed an unmeasured test-view fixture. Explicit view sizing and a gesture span based on ViewConfiguration corrected the fixture without changing production zoom behavior.
- Manual emulator checks passed for home, viewer, rotation/reset, bookmarks, saved collection, map previews, filters and retained tabs. Pinch zoom is also exercised through synthetic two-pointer device-test events.
- Fresh debug rotation sample:49 frames, histogram median23 ms, exact median23.363 ms, median UI draw0.801 ms and graphics-command issue1.297 ms. Janky frames43/49; no sustained60fps claim. The earlier Java22.133 ms sample is historical, not a controlled paired comparison.
- Independent read-only review compared all production classes against the Java baseline and found no Critical/Important defects. Historical source hyperlinks were changed to labeled plain references so they no longer point to removed files; current Kotlin source links are provided.
- README and AGENTS describe Kotlin and the current file names. Existing screenshots remain representative; no visible design change was introduced.
- Git has no commits; migration remains local in the current IDE workspace.
