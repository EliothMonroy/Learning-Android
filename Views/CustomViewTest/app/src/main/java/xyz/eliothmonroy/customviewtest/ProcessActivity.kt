package xyz.eliothmonroy.customviewtest

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_process.*

class ProcessActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_process)
        setupOnClickListeners()
    }

    private fun setupOnClickListeners() {
        login.setOnClickListener {
            val returnIntent=Intent()
            returnIntent.putExtra("UserInfo",UserInfo(user.text.toString(),pass.text.toString()))
            setResult(Activity.RESULT_OK,returnIntent)
            finish()
        }
    }
}
