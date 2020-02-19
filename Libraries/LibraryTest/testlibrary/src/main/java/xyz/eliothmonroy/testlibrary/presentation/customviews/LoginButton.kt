package xyz.eliothmonroy.testlibrary.presentation.customviews

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.widget.Button

class LoginButton(context:Context) : Button(context) {

    init {
        setBackgroundColor(Color.RED)
        setTextColor(Color.WHITE)
        text = "Login"
    }

}