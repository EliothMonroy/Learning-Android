package xyz.eliothmonroy.sesion3app.reto1

import Circulo
import Triangulo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_home.*
import xyz.eliothmonroy.sesion3app.R
import xyz.eliothmonroy.sesion3app.reto1.data.Rectangulo

class HomeActivity : AppCompatActivity() {

    private var idImg=0
    private var idButton=0
    private lateinit var parametros:LinearLayout.LayoutParams
    private var cont=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        parametros= LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        radioGroup.setOnCheckedChangeListener { _, checkedId -> generarContenido(checkedId) }
    }

    private fun generarContenido(checkedId: Int) {
        if(cont==0){
            cont+=1
        }else{
            limpiarContenido()
        }
        when(checkedId){
            radioCirculo.id->generarContenidoCirculo()
            radioRectangulo.id->generarContenidoRectangulo()
            radioTriangulo.id->generarContenidoTriangulo()
        }
    }

    private fun limpiarContenido() {
        contenedorCampos.removeAllViews()
        resultadoArea.text=""
        resultadoPerimetro.text=""
        contenedorResultado.removeViewAt(0)
    }

    private fun generarContenidoRectangulo() {

        val lado1=EditText(this)
        lado1.layoutParams=parametros
        lado1.hint=getString(R.string.hint_base_rectangulo)
        lado1.inputType=InputType.TYPE_CLASS_NUMBER+InputType.TYPE_NUMBER_FLAG_DECIMAL
        lado1.id=View.generateViewId()
        contenedorCampos.addView(lado1)

        val lado2=EditText(this)
        lado2.layoutParams=parametros
        lado2.id=View.generateViewId()
        lado2.hint=getString(R.string.hint_altura_rectangulo)
        lado2.inputType=InputType.TYPE_CLASS_NUMBER+InputType.TYPE_NUMBER_FLAG_DECIMAL
        contenedorCampos.addView(lado2)

        val img=ImageView(this)
        img.id=View.generateViewId()
        idImg=img.id
        img.setImageResource(R.drawable.ic_rectangle)
        img.layoutParams=parametros
        contenedorCampos.addView(img,0)

        val boton=Button(this)
        boton.id=View.generateViewId()
        idButton=boton.id
        boton.layoutParams=parametros
        boton.text=getString(R.string.texto_boton_calcular)
        contenedorResultado.addView(boton,0)

        boton.setOnClickListener {
            val rectangulo=Rectangulo(lado1.text.toString().toDouble(),lado2.text.toString().toDouble())
            resultadoArea.text=getString(R.string.placeholder_resultado_area,"%.2f".format(rectangulo.calcularArea()))
            resultadoPerimetro.text=getString(R.string.placeholder_resultado_perimetro,"%.2f".format(rectangulo.calcularPerimetro()))
        }
    }

    private fun generarContenidoTriangulo() {
        val lado1=EditText(this)
        lado1.layoutParams=parametros
        lado1.hint=getString(R.string.hint_lado1_triangulo)
        lado1.inputType=InputType.TYPE_CLASS_NUMBER+InputType.TYPE_NUMBER_FLAG_DECIMAL
        lado1.id=View.generateViewId()
        contenedorCampos.addView(lado1)

        val lado2=EditText(this)
        lado2.layoutParams=parametros
        lado2.id=View.generateViewId()
        lado2.hint=getString(R.string.hint_lado2_triangulo)
        lado2.inputType=InputType.TYPE_CLASS_NUMBER+InputType.TYPE_NUMBER_FLAG_DECIMAL
        contenedorCampos.addView(lado2)

        val lado3=EditText(this)
        lado3.layoutParams=parametros
        lado3.id=View.generateViewId()
        lado3.hint=getString(R.string.hint_lado3_triangulo)
        lado3.inputType=InputType.TYPE_CLASS_NUMBER+InputType.TYPE_NUMBER_FLAG_DECIMAL
        contenedorCampos.addView(lado3)

        val altura=EditText(this)
        altura.layoutParams=parametros
        altura.id=View.generateViewId()
        altura.hint=getString(R.string.hint_altura_triangulo)
        altura.inputType=InputType.TYPE_CLASS_NUMBER+InputType.TYPE_NUMBER_FLAG_DECIMAL
        contenedorCampos.addView(altura)

        val img=ImageView(this)
        img.id=View.generateViewId()
        idImg=img.id
        img.setImageResource(R.drawable.ic_triangle)
        img.layoutParams=parametros
        contenedorCampos.addView(img,0)

        val boton=Button(this)
        boton.id=View.generateViewId()
        idButton=boton.id
        boton.layoutParams=parametros
        boton.text=getString(R.string.texto_boton_calcular)
        contenedorResultado.addView(boton,0)

        boton.setOnClickListener {
            val triangulo=Triangulo(lado1.text.toString().toDouble(),lado2.text.toString().toDouble(),lado3.text.toString().toDouble(),altura.text.toString().toDouble())
            resultadoArea.text=getString(R.string.placeholder_resultado_area,"%.2f".format(triangulo.calcularArea()))
            resultadoPerimetro.text=getString(R.string.placeholder_resultado_perimetro,"%.2f".format(triangulo.calcularPerimetro()))
        }
    }

    private fun generarContenidoCirculo() {
        val radioTextView=EditText(this)
        radioTextView.layoutParams=parametros
        radioTextView.id=View.generateViewId()
        radioTextView.hint=getString(R.string.hint_radio_circulo)
        radioTextView.inputType=InputType.TYPE_CLASS_NUMBER+InputType.TYPE_NUMBER_FLAG_DECIMAL
        contenedorCampos.addView(radioTextView)

        val img=ImageView(this)
        img.id=View.generateViewId()
        idImg=img.id
        img.setImageResource(R.drawable.ic_circle)
        img.layoutParams=parametros
        contenedorCampos.addView(img,0)

        val boton=Button(this)
        boton.id=View.generateViewId()
        idButton=boton.id
        boton.layoutParams=parametros
        boton.text=getString(R.string.texto_boton_calcular)
        contenedorResultado.addView(boton,0)

        boton.setOnClickListener {
            val circulo=Circulo(radioTextView.text.toString().toDouble())
            resultadoArea.text=getString(R.string.placeholder_resultado_area,"%.2f".format(circulo.calcularArea()))
            resultadoPerimetro.text=getString(R.string.placeholder_resultado_perimetro,"%.2f".format(circulo.calcularPerimetro()))
        }
    }
}
