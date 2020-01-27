package xyz.eliothmonroy.examen.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import xyz.eliothmonroy.examen.R
import xyz.eliothmonroy.examen.adapters.UsuariosAdapter
import xyz.eliothmonroy.examen.model.data.Usuario
import xyz.eliothmonroy.examen.presenter.MainPresenter
import xyz.eliothmonroy.examen.presenter.MainPresenterImp

class MainActivity : AppCompatActivity(), MainView {

    private val mainPresenter:MainPresenter=MainPresenterImp(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainPresenter.obtenerUsuarios()
    }

    override fun mostrarUsuarios(listaUsuarios: List<Usuario>) {
        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.adapter=
            UsuariosAdapter(listaUsuarios)
    }

    override fun falloConexion(mensaje: String) {
        mostrarMensaje(mensaje)
    }

    override fun noHayUsuarios(mensaje: String) {
        mostrarMensaje(mensaje)
    }

    private fun mostrarMensaje(mensaje: String){
        Toast.makeText(this,mensaje,Toast.LENGTH_LONG).show()
    }

}
