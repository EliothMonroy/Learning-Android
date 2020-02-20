package xyz.eliothmonroy.testlibrary.presentation.customviews

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import androidx.appcompat.view.ContextThemeWrapper
import androidx.appcompat.widget.AppCompatButton
import xyz.eliothmonroy.testlibrary.R
import xyz.eliothmonroy.testlibrary.presentation.ProcessActivity


class CustomButton(context: Context, st:AttributeSet) :
    AppCompatButton(ContextThemeWrapper(context, R.style.CustomButton), st, 0) {

    init {
        setOnClickListener {
            val intent=Intent(context, ProcessActivity::class.java)
            (context as Activity).startActivityForResult(intent, REQUEST_CODE)
        }
    }

    companion object{
        const val REQUEST_CODE=0
    }

}