package xyz.eliothmonroy.aboutme

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import xyz.eliothmonroy.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val myName=MyName("Elioth Monroy")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        //Podemos remplazar el setContentView por el binding
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.myName=myName

//        findViewById<Button>(R.id.done_button).setOnClickListener {
//            addNickname(it)
//        }

        binding.doneButton.setOnClickListener {
            addNickname(it)
        }

    }

    private fun addNickname(view: View) {
        //val editText=findViewById<EditText>(R.id.nickname_edit)
        //val nicknameTextView=findViewById<TextView>(R.id.nickname_text)

        binding.apply {
            //nicknameText.text = nicknameEdit.text
            //We use data binding to set the value of the nickname
            myName?.nickname=nicknameEdit.text.toString()
            invalidateAll() //Invalidates all binding expressions and requests a new rebind to refresh UI.
            nicknameEdit.visibility = View.GONE
            doneButton.visibility = View.GONE
            nicknameText.visibility = View.VISIBLE
        }

        //Hide the keyboard
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
