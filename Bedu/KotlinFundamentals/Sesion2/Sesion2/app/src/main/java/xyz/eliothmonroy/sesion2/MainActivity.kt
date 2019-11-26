package xyz.eliothmonroy.sesion2

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val vocales:List<Char> = listOf('a','e','i','o','u')
    private val remplazo='x'

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        boton.setOnClickListener {
            if (textoIngresado.text.toString() == ""){
                showMessage(this,"Por favor ingrese alguna palabra")
            }else{
                esPalindromo(textoIngresado.text.toString().replace(" ","").toLowerCase(Locale("es")))
                resultado.text = getString(R.string.placeholder_resultado,remplazarVocales(textoIngresado.text.toString()))
            }
        }
    }

    private fun esPalindromo(palabra: String) {
        if(palabra==palabra.reversed()){
            showMessage(this,"La palabra/frase es palindroma")
        }else{
            showMessage(this,"La palabra/frase no es palindroma")
        }
    }

    private fun esPalindromoCiclo(palabra: String){
        var sigue=true
        var contador=1
        for (i in palabra){
            if (i!=palabra[palabra.length-contador]){
                sigue=false
                break
            }
            contador+=1
        }
        if(sigue){
            showMessage(this,"La palabra es palindroma")
        }else{
            showMessage(this,"La palabra no es palindroma")
        }
    }

    private fun remplazarVocales(palabra:String):String{
        return palabra.toLowerCase(Locale("es")).map { c -> if (c in vocales) remplazo else c}.joinToString("")
    }

}

fun showMessage(context:Context, texto:String){
    Toast.makeText(context,texto,Toast.LENGTH_SHORT).show()
}
