package xyz.eliothmonroy.tictaetoemvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import xyz.eliothmonroy.tictaetoemvvm.R

class GameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_activity)
    }
}
