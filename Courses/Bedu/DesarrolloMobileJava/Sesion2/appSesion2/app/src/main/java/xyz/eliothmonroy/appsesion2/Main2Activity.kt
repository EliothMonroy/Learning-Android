package xyz.eliothmonroy.appsesion2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {
    val OPERACIONES= arrayListOf(1,2,3,4)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        btnSuma.setOnClickListener {
            operacion(OPERACIONES[0])
        }
        btnResta.setOnClickListener {
            operacion(OPERACIONES[1])
        }
        btnProducto.setOnClickListener {
            operacion(OPERACIONES[2])
        }
        btnDividir.setOnClickListener {
            operacion(OPERACIONES[3])
        }
    }

    fun operacion(tipo:Int){
        val num1=txtNum1.text.toString().toDouble()
        val num2=txtNum2.text.toString().toDouble()
        var r= 0.0
        when(tipo){
            OPERACIONES[0]->r=num1+num2
            OPERACIONES[1]->r=num1-num2
            OPERACIONES[2]->r=num1*num2
            OPERACIONES[3]->if (num2>0) r=num1/num2 else r= Double.POSITIVE_INFINITY
        }
        Toast.makeText(applicationContext,"Resultado: ${r}", Toast.LENGTH_LONG).show()
    }

}
