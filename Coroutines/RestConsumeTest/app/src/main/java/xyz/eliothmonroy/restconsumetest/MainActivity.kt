package xyz.eliothmonroy.restconsumetest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import xyz.eliothmonroy.restconsumetest.async.AsyncActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this,AsyncActivity::class.java))
    }
}
