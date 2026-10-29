# Repository guidance

## Project

Distrito is a native Android Material 3 guide to Mexico City with six stylized, offline 3D landmarks. There is one application module, `:app`, with package `xyz.eliothmonroy.a3dtest`. Production code and tests use Kotlin with Android Views; preserve this approach unless a migration is requested.

## Main files

- `app/src/main/java/xyz/eliothmonroy/a3dtest/MainActivity.kt`: discovery, category filters, retained tabs, bookmarks, map and detail sheets.
- `app/src/main/java/xyz/eliothmonroy/a3dtest/LandmarkView.kt`: procedural meshes, projection, batched drawing, gestures and cached previews.
- `app/src/main/java/xyz/eliothmonroy/a3dtest/Attraction.kt`: offline attraction descriptions and map coordinates.
- `app/src/main/java/xyz/eliothmonroy/a3dtest/Symbol.kt`: vector-style icons.
- `app/src/main/res/values/`: Material 3 theme, app identity and resources.
- `app/src/androidTest/java/xyz/eliothmonroy/a3dtest/RenderingRegressionTest.kt`: rendering, cache, bookmark and retained-tab regressions.
- `performance-investigation.md`: original performance diagnosis and measured results after the fixes.
- `docs/screenshots/`: real emulator screenshots embedded in the README.

## Implementation conventions

- Preserve the ivory/deep-teal visual identity, Material 3 components, readable system bars and safe touch targets.
- Keep models and stories available offline. Map directions use an external map application.
- Treat the models as artistic interpretations, not surveyed architectural replicas.
- Keep immutable meshes shared, mutable rendering buffers owned by each view, and image caches bounded.
- Preserve the single triangle batch for interactive models. Avoid per-face Path fills/strokes, allocations inside drawing loops, and rebuilding the whole screen for a bookmark update.
- Keep category selection, saved state and tab navigation consistent. Preserve tab scroll positions when changing screens.
- Preserve unrelated edits. When Android Studio workspace tools are available, use their reads and edits so unsaved IDE buffers remain authoritative.
- Do not add signing keys, local SDK paths, credentials, caches or build artifacts to Git. Publish or push only when requested.

## Build and checks

The wrapper uses Gradle 9.6.0, AGP 9.5.0 with built-in Kotlin support, and a configured JDK 25 daemon toolchain. Kotlin JVM output follows the existing Java 11 targetCompatibility; do not add the legacy kotlin-android plugin. The app compiles/targets SDK 37 and runs on API 29 and newer.

```sh
./gradlew :app:assembleDebug
./gradlew :app:testDebugUnitTest :app:lintDebug
./gradlew :app:connectedDebugAndroidTest
```

Device tests need an online emulator or physical device. On Windows, use `gradlew.bat`. Run the checks appropriate to the change and report the actual results.

For renderer changes, also inspect home, the interactive viewer and map previews on a running device. Exercise rotation, reset, zoom, bookmarks, categories and tab switching. Compare the same rotation reproduction with fresh frame statistics; do not infer performance from build or screenshot success alone. Report device/build context and distinguish emulator measurements from physical-device release performance.

## Documentation

Keep README commands aligned with Gradle configuration. Refresh screenshots from the running app when the visible design changes; use relative links so they render in Git hosting. Keep historical performance measurements labeled and append new evidence rather than presenting earlier timings as current.
