package xyz.eliothmonroy.sesion3.reto1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import xyz.eliothmonroy.sesion3.R

class MainActivity : AppCompatActivity() {
    private lateinit var timer: CountDownTimer
    val TAG = "TIMER"

    private fun setTimer(){
        timer = object: CountDownTimer(20000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished/1000
                Log.d(TAG,"Tiempo restante: $seconds")
            }
            override fun onFinish() {
                Log.d(TAG,"Contador finalizado")
            }
        }
        timer.start()
    }

    private fun stopTimer(){
        timer.cancel()
        Log.d(TAG,"timer cancelado")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG,"Entre a onCreate()")
        setTimer()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"Entre a onDestroy()")
        stopTimer()
    }
}
