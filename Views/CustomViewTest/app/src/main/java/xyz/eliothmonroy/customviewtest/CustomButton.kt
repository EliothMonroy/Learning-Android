package xyz.eliothmonroy.customviewtest

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.Button
import androidx.appcompat.view.ContextThemeWrapper
import androidx.appcompat.widget.AppCompatButton


class CustomButton(context: Context, st:AttributeSet) : AppCompatButton(ContextThemeWrapper(context, R.style.CustomButton), st, 0) {

    init {
        isClickable=true
        isEnabled=true

    }

}