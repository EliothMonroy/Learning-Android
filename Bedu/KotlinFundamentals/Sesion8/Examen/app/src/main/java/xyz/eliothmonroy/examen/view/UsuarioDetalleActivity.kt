package xyz.eliothmonroy.examen.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_usuario_detalle.*
import xyz.eliothmonroy.examen.R
import xyz.eliothmonroy.examen.adapters.CuentasAdapter
import xyz.eliothmonroy.examen.model.data.Cuentas

class UsuarioDetalleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuario_detalle)
        supportActionBar?.setHomeButtonEnabled(true)
        intent.extras?.let {
            recyclerViewCuentas.layoutManager= LinearLayoutManager(this)
            it.getString("Nombre")?.let {nombre->
                usuarioDetalleNombre.text=getString(R.string.placeholder_usuario_nombre,nombre)
            }

            it.getParcelableArrayList<Cuentas>("Cuentas")?.let {cuentas->
                recyclerViewCuentas.adapter=
                    CuentasAdapter(cuentas)
            }
        }
    }
}
