package xyz.eliothmonroy.testlibrary.presentation

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_process.*
import xyz.eliothmonroy.testlibrary.R
import xyz.eliothmonroy.testlibrary.data.model.Todo
import xyz.eliothmonroy.testlibrary.data.model.UserInfo
import xyz.eliothmonroy.testlibrary.presentation.viewmodel.TodoViewModel

class ProcessActivity : AppCompatActivity() {

    private lateinit var todoViewModel:TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_process)
        todoViewModel = ViewModelProvider(this)[TodoViewModel::class.java]
        setupOnClickListeners()
    }

    private fun setupOnClickListeners() {
        login.setOnClickListener {
            todoViewModel.todo.observe(this, Observer {response->
                returnToActivity(response.todo)
            })
        }
    }

    private fun returnToActivity(todo: Todo){
        Log.d("Library:",todo.toString())
        val returnIntent= Intent()
        returnIntent.putExtra("UserInfo", UserInfo(user.text.toString(),pass.text.toString()))
        setResult(Activity.RESULT_OK,returnIntent)
        finish()
    }
}