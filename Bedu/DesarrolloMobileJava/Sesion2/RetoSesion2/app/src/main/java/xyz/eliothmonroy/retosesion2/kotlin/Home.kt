package xyz.eliothmonroy.retosesion2.kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_home.*
import xyz.eliothmonroy.retosesion2.R

class Home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        enviar.setOnClickListener {
            val edadNum=edad.text?.toString()?.toInt()?:"Vacio"
            when (edadNum){
                "Vacio"->Toast.makeText(this,"Ingrese un número por favor",Toast.LENGTH_LONG).show()
                 in 1..17->startActivity(Intent(this@Home,MenorActivity::class.java))
                 else->startActivity(Intent(this@Home,MayorActivity::class.java))
            }
        }

    }
}
