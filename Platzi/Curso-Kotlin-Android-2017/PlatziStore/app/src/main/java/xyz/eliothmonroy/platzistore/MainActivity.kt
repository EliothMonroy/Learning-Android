package xyz.eliothmonroy.platzistore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.* //Importamos los views desde este activity, podemos accesar a ellos por id

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView.text="Hola Android Extensions"
    }
}
