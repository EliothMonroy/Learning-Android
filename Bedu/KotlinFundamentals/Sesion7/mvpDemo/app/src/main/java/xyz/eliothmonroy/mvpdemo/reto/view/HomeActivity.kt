package xyz.eliothmonroy.mvpdemo.reto.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_home.*
import xyz.eliothmonroy.mvpdemo.R
import xyz.eliothmonroy.mvpdemo.reto.model.data.Usuario
import xyz.eliothmonroy.mvpdemo.reto.presenter.UsuarioPresenter
import xyz.eliothmonroy.mvpdemo.reto.presenter.UsuarioPresenterImp

class HomeActivity : AppCompatActivity(), HomeView {

    private val usuarioPresenter:UsuarioPresenter= UsuarioPresenterImp(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        usuarioPresenter.obtenerUsuarios()
        botonRegistroUsuario.setOnClickListener {
            iniciarSesion(usuarioNombre.text.toString(),usuarioEmail.text.toString(),usuarioTelefono.text.toString())
        }
    }

    override fun iniciarSesion(nombre: String, email: String, telefono: String) {
        usuarioPresenter.iniciarSesion(nombre,email,telefono)
    }

    override fun mostrarUsuario(usuario: Usuario) {
        val view=TextView(this)
        view.text=usuario.email
        linearListaUsuarios.addView(view)
    }

    override fun mostrarUsuarios(listaUsuarios: MutableList<Usuario>) {
        listaUsuarios.forEach {
            Log.d("USUARIO",it.email)
            val view=TextView(this)
            view.text=it.email
            linearListaUsuarios.addView(view)
        }
    }

    override fun mostrarMensaje(mensaje: String) {
        Toast.makeText(this,mensaje,Toast.LENGTH_LONG).show()
    }

    override fun registroExitoso() {
        Toast.makeText(this,"Usuario registrado exitosamente",Toast.LENGTH_LONG).show()
    }

    override fun registroNoExitoso() {
        Toast.makeText(this,"El email ya exite, no es posible registrarte",Toast.LENGTH_LONG).show()
    }
}
