package xyz.eliothmonroy.customviewtest

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.Button
import androidx.appcompat.view.ContextThemeWrapper


class CustomButton(context: Context, st:AttributeSet) : Button(
    ContextThemeWrapper(
        context,
        //androidx.appcompat.R.style.Widget_AppCompat_Button_Colored
        R.style.CustomButton
    ),
    st,
    0
) {

    init {
        //setBackgroundColor(Color.RED)
        setTextColor(Color.WHITE)
        text = "Login"
        isClickable=true
        isEnabled=true
        //TODO: No se ve el color en android 5
        //background.setTint(Color.RED)
    }

}