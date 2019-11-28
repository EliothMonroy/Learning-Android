package xyz.eliothmonroy.sesion4app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        miObjeto.saludar()
        miObjeto.saludar()

        Log.d("MainActivity",miObjeto.URL_BASE)

        val miObjetoDos=MiObjetoDos.saludar("Objeto2")
        val miObjetoDos2=MiObjetoDos.saludar("Objeto2")

        Log.d("MainActivity",miObjetoDos.nombre)
        Log.d("MainActivity","${MiObjetoDos.cont}")



    }
}
