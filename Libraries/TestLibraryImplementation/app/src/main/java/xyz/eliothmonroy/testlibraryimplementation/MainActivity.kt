package xyz.eliothmonroy.testlibraryimplementation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import xyz.eliothmonroy.testlibrary.data.model.Response
import xyz.eliothmonroy.testlibraryimplementation.presentation.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel:MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        showTodo()

    }

    fun showTodo(){
        mainViewModel.todo.observe(this, Observer {response->
            Log.d("Main",response.toString())
        })
    }

}
