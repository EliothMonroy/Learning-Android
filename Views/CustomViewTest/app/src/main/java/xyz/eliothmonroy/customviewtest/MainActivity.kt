package xyz.eliothmonroy.customviewtest

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==CustomButton.REQUEST_CODE){
            if (resultCode==Activity.RESULT_OK){
                if (data!=null){
                    val userInfo=data.extras!!.get("UserInfo")
                    Log.d("Main",userInfo.toString())
                }
            }
        }
    }
}
