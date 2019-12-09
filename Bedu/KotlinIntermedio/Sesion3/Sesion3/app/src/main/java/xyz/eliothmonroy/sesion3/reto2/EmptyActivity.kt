package xyz.eliothmonroy.sesion3.reto2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_empty.*
import xyz.eliothmonroy.sesion3.R

class EmptyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empty)
        val fragmentManager = supportFragmentManager //creando el Fragment Manager
        var fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = MyFragment() //Creando una instancia de mi Fragmento
        fragmentTransaction.add(android.R.id.content, fragment) //Agrego mi fragmento al Activity a través de mi Transaction
        fragmentTransaction.commit() //ejecuto los cambios
        button.setOnClickListener {
            fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(android.R.id.content, Fragment2()) //Agrego mi fragmento al Activity a través de mi Transaction
            fragmentTransaction.commit() //ejecuto los cambios
        }

    }
}
