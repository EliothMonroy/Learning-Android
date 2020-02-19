package xyz.eliothmonroy.customviewtest

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.AttributeSet
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat

class CustomButton(context: Context, st:AttributeSet) : Button(context,st) {

    init {
        //setBackgroundColor(Color.RED)
        setTextColor(Color.WHITE)
        text = "Login"
        isClickable=true
        isEnabled=true
        //TODO: No se ve el color en android 5
        background.setTint(Color.RED)
    }

}