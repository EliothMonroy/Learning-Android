package xyz.eliothmonroy.kotlinyjava

import android.content.Context
import androidx.appcompat.app.AppCompatActivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var edtxUsuario:EditText
    private lateinit var edtxPassword:EditText
    private lateinit var btnLogin:Button
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edtxUsuario = findViewById(R.id.edtxLoginUsuario)
        edtxPassword = findViewById(R.id.edtxLoginPassword)
        btnLogin = findViewById(R.id.btnLogin)

        val sharedPreferences=getSharedPreferences("kotlinpre", Context.MODE_PRIVATE)
        //Verificamos si ya nos habiamos loggeado
        val isLogged=sharedPreferences.getBoolean("loggeado",false)
        if (isLogged){
            //startActivity(Intent(this@MainActivity, ListadoActivity::class.java))
            finish()
        }
        btnLogin.setOnClickListener {
            //boolean esValido=Validaciones.INSTANCE.passwordValida(edtxPassword);//Sin la anotación
            val esValido = edtxPassword.passwordValida()
            Log.w(TAG, "onClick: $esValido")
            if (esValido) {
                //iniciar vista
                sharedPreferences.edit(){
                    putBoolean("loggeado",true)
                }
                val intent=Intent(this@MainActivity, ListadoActivity::class.java)
                intent.putExtra("usuario",edtxUsuario.text.toString())
                startActivity(intent)
                //startActivity(Intent(this@MainActivity, ListadoActivity::class.java))
            } else {
                Toast.makeText(this@MainActivity, "Password inválido", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
