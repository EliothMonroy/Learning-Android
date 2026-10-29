package xyz.eliothmonroy.a3dtest

import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable

/** Resolution-independent icons for navigation and actions. */
internal class Symbol(private val name: String, private val color: Int) : Drawable() {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG)

    override fun draw(canvas: Canvas) {
        val b = bounds
        canvas.save()
        canvas.translate(b.left.toFloat(), b.top.toFloat())
        canvas.scale(b.width() / 24f, b.height() / 24f)
        paint.color = color
        paint.strokeWidth = 1.7f
        paint.strokeCap = Paint.Cap.ROUND
        paint.strokeJoin = Paint.Join.ROUND
        paint.style = Paint.Style.STROKE
        val path = Path()
        when (name) {
            "heart", "heartFill" -> {
                path.moveTo(12f, 20f)
                path.cubicTo(9f, 17f, 2f, 13f, 3f, 7f)
                path.cubicTo(4f, 2f, 10f, 2f, 12f, 6f)
                path.cubicTo(15f, 1f, 21f, 3f, 21f, 8f)
                path.cubicTo(21f, 13f, 15f, 18f, 12f, 20f)
                if (name == "heartFill") paint.style = Paint.Style.FILL
                canvas.drawPath(path, paint)
            }
            "compass" -> {
                canvas.drawCircle(12f, 12f, 9f, paint)
                path.moveTo(16f, 7f)
                path.lineTo(14f, 14f)
                path.lineTo(7f, 17f)
                path.lineTo(10f, 10f)
                path.close()
                canvas.drawPath(path, paint)
            }
            "map" -> {
                path.moveTo(3f, 5f)
                path.lineTo(9f, 3f)
                path.lineTo(15f, 6f)
                path.lineTo(21f, 3f)
                path.lineTo(21f, 19f)
                path.lineTo(15f, 22f)
                path.lineTo(9f, 19f)
                path.lineTo(3f, 21f)
                path.close()
                canvas.drawPath(path, paint)
                canvas.drawLine(9f, 3f, 9f, 19f, paint)
                canvas.drawLine(15f, 6f, 15f, 22f, paint)
            }
            "info" -> {
                canvas.drawCircle(12f, 12f, 9f, paint)
                canvas.drawLine(12f, 11f, 12f, 17f, paint)
                canvas.drawPoint(12f, 7f, paint)
            }
            "close" -> {
                canvas.drawLine(6f, 6f, 18f, 18f, paint)
                canvas.drawLine(18f, 6f, 6f, 18f, paint)
            }
            else -> {
                path.moveTo(12f, 2f)
                path.lineTo(22f, 7f)
                path.lineTo(22f, 17f)
                path.lineTo(12f, 22f)
                path.lineTo(2f, 17f)
                path.lineTo(2f, 7f)
                path.close()
                canvas.drawPath(path, paint)
                canvas.drawLine(2f, 7f, 12f, 12f, paint)
                canvas.drawLine(22f, 7f, 12f, 12f, paint)
                canvas.drawLine(12f, 12f, 12f, 22f, paint)
            }
        }
        canvas.restore()
    }

    override fun setAlpha(alpha: Int) { paint.alpha = alpha }
    override fun setColorFilter(colorFilter: ColorFilter?) { paint.colorFilter = colorFilter }
    @Suppress("DEPRECATION")
    override fun getOpacity(): Int = PixelFormat.TRANSLUCENT
    override fun getIntrinsicWidth(): Int = 24
    override fun getIntrinsicHeight(): Int = 24
}
