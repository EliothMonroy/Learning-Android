package xyz.eliothmonroy.restconsumetest.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import xyz.eliothmonroy.restconsumetest.R
import xyz.eliothmonroy.restconsumetest.coroutines.presentation.viewmodel.CoroutinesViewModel

class CoroutinesActivity : AppCompatActivity() {

    lateinit var coroutinesViewModel: CoroutinesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines)

        coroutinesViewModel= ViewModelProvider(this)[CoroutinesViewModel::class.java]

        showTodo()

    }

    private fun showTodo() {
        coroutinesViewModel.getTodo2().observe(this, Observer {response->
            Log.d("Coroutines: ",response.toString())
            if (response.isSuccessful){
                Log.d("Coroutines: ",response.body()!!.toString())
            }else{
                Log.d("Coroutines: ",response.body()!!.toString())
            }
        })
    }

}
