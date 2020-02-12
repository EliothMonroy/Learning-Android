package xyz.eliothmonroy.restconsumetest.async

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import xyz.eliothmonroy.restconsumetest.R
import xyz.eliothmonroy.restconsumetest.async.view.viewmodel.AsyncViewModel

class AsyncActivity : AppCompatActivity() {

    lateinit var asyncViewModel: AsyncViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async)

        asyncViewModel= ViewModelProvider(this)[AsyncViewModel::class.java]

        showTodo()

    }

    private fun showTodo() {
        asyncViewModel.getTodo().observe(this, Observer {
            Log.d("Async:",it.toString())
        })
    }
}
