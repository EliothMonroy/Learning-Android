package xyz.eliothmonroy.a3dtest

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.graphics.RectF
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ScrollView
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.*
import org.junit.Test

class RenderingRegressionTest {
    private val instrumentation get() = InstrumentationRegistry.getInstrumentation()

    /** Guards against thousands of individually prepared fill/stroke paths. */
    @Test fun interactiveModelSubmitsOneTriangleBatch() {
        instrumentation.runOnMainSync {
            val view = LandmarkView(instrumentation.targetContext, "palace", true)
            view.layout(0, 0, 600, 420)
            val canvas = CountingCanvas()
            view.draw(canvas)
            assertEquals("No per-polygon paths on RenderThread", 0, canvas.paths)
            assertEquals("One triangle batch per model", 1, canvas.batches)
            assertTrue("Model must still draw visible geometry", canvas.vertices > 100)
            assertTrue("Output cannot be an empty optimized frame", hasOpaquePixel(canvas.bitmap))
        }
    }

    @Test fun staticPreviewReusesRasterOnSubsequentDraw() {
        instrumentation.runOnMainSync {
            val view = LandmarkView(instrumentation.targetContext, "palace", false)
            view.layout(0, 0, 600, 420)
            view.draw(CountingCanvas())
            val second = CountingCanvas()
            view.invalidate()
            view.draw(second)
            assertEquals("Static previews must not prepare polygon paths again", 0, second.paths)
            assertEquals("Static previews must not resubmit the mesh", 0, second.batches)
            assertEquals("A cached image supplies the preview", 1, second.bitmaps)
            assertTrue(hasOpaquePixel(second.bitmap))
        }
    }

    @Test fun bookmarkTapRetainsTheVisibleModel() {
        val activity = launchActivity()
        try {
            instrumentation.runOnMainSync {
                val root = activity.findViewById<ViewGroup>(android.R.id.content)
                val before = requireNotNull(findModel(root))
                requireNotNull(findBookmark(root)).performClick()
                val after = findModel(root)
                // Restore the original saved state before asserting.
                requireNotNull(findBookmark(root)).performClick()
                assertSame("Bookmark changes must not reconstruct the screen", before, after)
            }
        } finally {
            instrumentation.runOnMainSync { activity.finish() }
        }
    }

    @Test fun rotationChangesPixelsAndResetRestoresTheCamera() {
        instrumentation.runOnMainSync {
            for (model in listOf("palace", "angel", "castle", "soumaya", "cathedral", "revolution")) {
                val view = LandmarkView(instrumentation.targetContext, model, true)
                view.layout(0, 0, 600, 420)
                val initial = CountingCanvas()
                view.draw(initial)
                assertTrue("$model must render opaque geometry", hasOpaquePixel(initial.bitmap))
                view.rotate(0.7f)
                val rotated = CountingCanvas()
                view.draw(rotated)
                assertFalse("$model rotation must update the rendered camera", initial.bitmap.sameAs(rotated.bitmap))
                view.reset()
                val reset = CountingCanvas()
                view.draw(reset)
                assertTrue("$model reset must restore the original output", initial.bitmap.sameAs(reset.bitmap))
                view.reset()
                val repeated = CountingCanvas()
                view.draw(repeated)
                assertTrue("$model repeated reset must be stable", reset.bitmap.sameAs(repeated.bitmap))
            }
        }
    }

    @Test fun previewCacheSeparatesModelsAndRespondsToResize() {
        instrumentation.runOnMainSync {
            val context = instrumentation.targetContext
            val palace = LandmarkView(context, "palace", false)
            palace.layout(0, 0, 600, 420)
            val large = CountingCanvas()
            palace.draw(large)
            val duplicate = LandmarkView(context, "palace", false)
            duplicate.layout(0, 0, 600, 420)
            val cached = CountingCanvas()
            duplicate.draw(cached)
            assertSame("Equal previews should share the cached raster", large.lastBitmap, cached.lastBitmap)
            val angel = LandmarkView(context, "angel", false)
            angel.layout(0, 0, 600, 420)
            val other = CountingCanvas()
            angel.draw(other)
            assertFalse("Cache keys must distinguish landmarks", large.bitmap.sameAs(other.bitmap))
            palace.layout(0, 0, 300, 210)
            val resized = CountingCanvas()
            palace.draw(resized)
            assertNotSame("A resized preview needs its own raster", large.lastBitmap, resized.lastBitmap)
            assertTrue(hasOpaquePixel(resized.bitmap))
            assertEquals("Resized output must fit new view bounds", 0, resized.bitmap.getPixel(450, 300))
        }
    }

    @Test fun pinchGestureZoomsAndResetRestoresTheModel() {
        instrumentation.runOnMainSync {
            val context = instrumentation.targetContext
            val view = LandmarkView(context, "palace", true)
            android.widget.FrameLayout(context).apply {
                addView(view, android.widget.FrameLayout.LayoutParams(600, 420))
                layout(0, 0, 600, 420)
            }
            view.layout(0, 0, 600, 420)
            val initial = CountingCanvas()
            view.draw(initial)
            assertTrue("The zoom fixture must render visible geometry", hasOpaquePixel(initial.bitmap))
            val startSpan = maxOf(200f, android.view.ViewConfiguration.get(context).scaledMinimumScalingSpan + 50f)
            val endSpan = startSpan * 2.5f
            val downTime = android.os.SystemClock.uptimeMillis()
            val properties = Array(2) { index ->
                android.view.MotionEvent.PointerProperties().apply {
                    id = index
                    toolType = android.view.MotionEvent.TOOL_TYPE_FINGER
                }
            }
            val coordinates = Array(2) {
                android.view.MotionEvent.PointerCoords().apply { y = 210f; pressure = 1f; size = 1f }
            }
            var time = downTime
            fun send(action: Int, count: Int, span: Float) {
                coordinates[0].x = 300f - span / 2
                coordinates[1].x = 300f + span / 2
                val event = android.view.MotionEvent.obtain(
                    downTime, time, action, count, properties, coordinates,
                    0, 0, 1f, 1f, 0, 0, android.view.InputDevice.SOURCE_TOUCHSCREEN, 0,
                )
                try { view.onTouchEvent(event) } finally { event.recycle() }
                time += 16
            }
            send(android.view.MotionEvent.ACTION_DOWN, 1, startSpan)
            send(android.view.MotionEvent.ACTION_POINTER_DOWN or
                (1 shl android.view.MotionEvent.ACTION_POINTER_INDEX_SHIFT), 2, startSpan)
            for (step in 1..12) send(android.view.MotionEvent.ACTION_MOVE, 2,
                startSpan + (endSpan - startSpan) * step / 12)
            send(android.view.MotionEvent.ACTION_POINTER_UP or
                (1 shl android.view.MotionEvent.ACTION_POINTER_INDEX_SHIFT), 2, endSpan)
            send(android.view.MotionEvent.ACTION_UP, 1, endSpan)
            val zoomed = CountingCanvas()
            view.draw(zoomed)
            fun opaqueSamples(bitmap: Bitmap): Int {
                var count = 0
                for (y in 0 until bitmap.height step 4) for (x in 0 until bitmap.width step 4) {
                    if (bitmap.getPixel(x, y) ushr 24 == 255) count++
                }
                return count
            }
            assertTrue("A spreading pinch must enlarge visible geometry",
                opaqueSamples(zoomed.bitmap) > opaqueSamples(initial.bitmap) * 1.3f)
            view.reset()
            val reset = CountingCanvas()
            view.draw(reset)
            assertTrue("Reset must restore the camera after zoom", initial.bitmap.sameAs(reset.bitmap))
        }
    }

    @Test fun switchingTabsReusesModelAndScrollPosition() {
        val activity = launchActivity()
        lateinit var original: LandmarkView
        try {
            instrumentation.waitForIdleSync()
            instrumentation.runOnMainSync {
                val root = activity.findViewById<ViewGroup>(android.R.id.content)
                original = requireNotNull(findModel(root))
                requireNotNull(findScroll(root)).scrollTo(0, 200)
                activity.findViewById<View>(R.id.nav_map).performClick()
            }
            instrumentation.waitForIdleSync()
            instrumentation.runOnMainSync { activity.findViewById<View>(R.id.nav_discover).performClick() }
            instrumentation.waitForIdleSync()
            instrumentation.runOnMainSync {
                val root = activity.findViewById<ViewGroup>(android.R.id.content)
                assertSame(original, findModel(root))
                assertEquals("Returning to a tab restores its position", 200, requireNotNull(findScroll(root)).scrollY)
            }
        } finally {
            instrumentation.runOnMainSync { activity.finish() }
        }
    }

    private fun launchActivity(): Activity {
        val intent = Intent(instrumentation.targetContext, MainActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        return instrumentation.startActivitySync(intent)
    }

    private fun findScroll(view: View): ScrollView? {
        if (view is ScrollView) return view
        if (view is ViewGroup) for (i in 0 until view.childCount) {
            findScroll(view.getChildAt(i))?.let { return it }
        }
        return null
    }

    private fun findModel(view: View): LandmarkView? {
        if (view is LandmarkView) return view
        if (view is ViewGroup) for (i in 0 until view.childCount) {
            findModel(view.getChildAt(i))?.let { return it }
        }
        return null
    }

    private fun findBookmark(view: View): ImageButton? {
        val description = view.contentDescription?.toString()
        if (view is ImageButton && description != null &&
            (description.startsWith("Save ") || description.startsWith("Remove "))) return view
        if (view is ViewGroup) for (i in 0 until view.childCount) {
            findBookmark(view.getChildAt(i))?.let { return it }
        }
        return null
    }

    private fun hasOpaquePixel(bitmap: Bitmap): Boolean {
        for (y in 0 until bitmap.height step 8) for (x in 0 until bitmap.width step 8) {
            if (bitmap.getPixel(x, y) ushr 24 == 255) return true
        }
        return false
    }

    private class CountingCanvas(
        val bitmap: Bitmap = Bitmap.createBitmap(600, 420, Bitmap.Config.ARGB_8888),
    ) : Canvas(bitmap) {
        var paths = 0
        var batches = 0
        var vertices = 0
        var bitmaps = 0
        var lastBitmap: Bitmap? = null

        override fun drawPath(path: Path, paint: Paint) {
            paths++
            super.drawPath(path, paint)
        }

        override fun drawVertices(
            mode: VertexMode, vertexCount: Int, verts: FloatArray, vertOffset: Int,
            texs: FloatArray?, texOffset: Int, colors: IntArray?, colorOffset: Int,
            indices: ShortArray?, indexOffset: Int, indexCount: Int, paint: Paint,
        ) {
            batches++
            vertices += vertexCount / 2
            super.drawVertices(mode, vertexCount, verts, vertOffset, texs, texOffset, colors,
                colorOffset, indices, indexOffset, indexCount, paint)
        }

        override fun drawBitmap(bitmap: Bitmap, src: Rect?, dst: RectF, paint: Paint?) {
            bitmaps++
            lastBitmap = bitmap
            super.drawBitmap(bitmap, src, dst, paint)
        }
    }
}
