package xyz.eliothmonroy.restconsume

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import xyz.eliothmonroy.restconsume.coroutines.presentation.fragment.CoroutinesFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState==null){
            supportFragmentManager
                .beginTransaction()
                .add(CoroutinesFragment(),CoroutinesFragment.TAG)
                .commit()
        }
    }
}
