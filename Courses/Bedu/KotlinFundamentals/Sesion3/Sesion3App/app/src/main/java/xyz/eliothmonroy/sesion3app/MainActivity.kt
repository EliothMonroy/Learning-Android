package xyz.eliothmonroy.sesion3app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var cont:Int=1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnGenerar.setOnClickListener { generarItem() }
    }

    private fun generarItem() {
        val boton=Button(this)
        val parametros=LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT)
        boton.layoutParams=parametros
        val img=ImageView(this)
        img.setImageResource(R.drawable.ic_domain_black_24dp)
        img.layoutParams=parametros

        boton.id=View.generateViewId()
        boton.text=getString(R.string.placeholder_boton,cont)

        boton.setOnClickListener {
            Toast.makeText(this,"El id del boton es: ${it.id}",Toast.LENGTH_SHORT).show()
        }

        if(cont==1){
            contenedor.addView(img)
        }

        contenedor.addView(boton)



        cont+=1

    }
}
