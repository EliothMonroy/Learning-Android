package xyz.eliothmonroy.sesion4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            obtenerTexto()
        }

    }

    private fun obtenerTexto() {
        if(editText.text.isNotEmpty()){
            editText.visibility= View.GONE
            textView.text=editText.text.toString()
        }else{
            Toast.makeText(this,"Por favor ingrese un valor", Toast.LENGTH_SHORT).show()
        }
    }
}
