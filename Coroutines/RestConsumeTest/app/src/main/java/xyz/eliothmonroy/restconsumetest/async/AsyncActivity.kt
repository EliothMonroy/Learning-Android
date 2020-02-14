package xyz.eliothmonroy.restconsumetest.async

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import xyz.eliothmonroy.restconsumetest.R
import xyz.eliothmonroy.restconsumetest.async.presentation.viewmodel.AsyncViewModel

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
