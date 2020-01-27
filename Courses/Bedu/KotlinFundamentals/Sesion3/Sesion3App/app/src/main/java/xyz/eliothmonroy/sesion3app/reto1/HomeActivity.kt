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

    private lateinit var parametros:LinearLayout.LayoutParams
    private lateinit var parametrosImagen:LinearLayout.LayoutParams
    private var primeraVez=true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        parametros= LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        parametrosImagen= LinearLayout.LayoutParams(200, 200)
        radioGroup.setOnCheckedChangeListener { _, checkedId -> generarContenido(checkedId) }
    }

    private fun generarContenido(checkedId: Int) {
        if(primeraVez){
            primeraVez=primeraVez.not()
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

    private fun crearEditTextNumerico(hint:String):EditText{
        val editTextNumerico=EditText(this)
        editTextNumerico.layoutParams=parametros
        editTextNumerico.hint=hint
        editTextNumerico.inputType=InputType.TYPE_CLASS_NUMBER+InputType.TYPE_NUMBER_FLAG_DECIMAL
        editTextNumerico.id=View.generateViewId()
        return editTextNumerico

    }

    private fun crearImageView(imageResource:Int):ImageView{
        val imageView=ImageView(this)
        imageView.id=View.generateViewId()
        imageView.setImageResource(imageResource)
        imageView.layoutParams=parametrosImagen
        return imageView
    }

    private fun crearButtonView():Button{
        val button=Button(this)
        button.id=View.generateViewId()
        button.layoutParams=parametros
        button.text=getString(R.string.texto_boton_calcular)
        return button
    }

    private fun generarContenidoRectangulo() {

        val lado1=crearEditTextNumerico(getString(R.string.hint_base_rectangulo))
        contenedorCampos.addView(lado1)

        val lado2=crearEditTextNumerico(getString(R.string.hint_altura_rectangulo))
        contenedorCampos.addView(lado2)

        val img=crearImageView(R.drawable.ic_rectangle)
        contenedorCampos.addView(img,0)

        val boton=crearButtonView()
        contenedorResultado.addView(boton,0)

        boton.setOnClickListener {
            val rectangulo=Rectangulo(lado1.text.toString().toDouble(),lado2.text.toString().toDouble())
            resultadoArea.text=getString(R.string.placeholder_resultado_area,rectangulo.calcularArea())
            resultadoPerimetro.text=getString(R.string.placeholder_resultado_perimetro,rectangulo.calcularPerimetro())
        }
    }

    private fun generarContenidoTriangulo() {
        val lado1=crearEditTextNumerico(getString(R.string.hint_lado1_triangulo))
        contenedorCampos.addView(lado1)

        val lado2=crearEditTextNumerico(getString(R.string.hint_lado2_triangulo))
        contenedorCampos.addView(lado2)

        val lado3=crearEditTextNumerico(getString(R.string.hint_lado3_triangulo))
        contenedorCampos.addView(lado3)

        val altura=crearEditTextNumerico(getString(R.string.hint_altura_triangulo))
        contenedorCampos.addView(altura)

        val img=crearImageView(R.drawable.ic_triangle)
        contenedorCampos.addView(img,0)

        val boton=crearButtonView()
        contenedorResultado.addView(boton,0)

        boton.setOnClickListener {
            val triangulo=Triangulo(lado1.text.toString().toDouble(),lado2.text.toString().toDouble(),lado3.text.toString().toDouble(),altura.text.toString().toDouble())
            resultadoArea.text=getString(R.string.placeholder_resultado_area,triangulo.calcularArea())
            resultadoPerimetro.text=getString(R.string.placeholder_resultado_perimetro,triangulo.calcularPerimetro())
        }
    }

    private fun generarContenidoCirculo() {
        val radio=crearEditTextNumerico(getString(R.string.hint_radio_circulo))
        contenedorCampos.addView(radio)

        val img=crearImageView(R.drawable.ic_circle)
        contenedorCampos.addView(img,0)

        val boton=crearButtonView()
        contenedorResultado.addView(boton,0)

        boton.setOnClickListener {
            val circulo=Circulo(radio.text.toString().toDouble())
            resultadoArea.text=getString(R.string.placeholder_resultado_area,circulo.calcularArea())
            resultadoPerimetro.text=getString(R.string.placeholder_resultado_perimetro,circulo.calcularPerimetro())
        }
    }
}
