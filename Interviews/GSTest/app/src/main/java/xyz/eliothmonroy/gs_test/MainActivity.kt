package xyz.eliothmonroy.gs_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import xyz.eliothmonroy.gs_test.test.presentation.fragment.TestFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState==null){
            supportFragmentManager.beginTransaction()
                .add(TestFragment(),TestFragment.TAG)
                .commit()
        }
    }
}
