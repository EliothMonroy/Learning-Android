package xyz.eliothmonroy.floatingactivitylibrarytest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager

class FloatingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_floating)
        setWindowParams()
    }

    private fun setWindowParams() {
        val wlp = window.attributes
        wlp.dimAmount = 0f
        wlp.flags = WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS or
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
        window.attributes = wlp
    }
}
