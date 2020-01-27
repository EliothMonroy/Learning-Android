package xyz.eliothmonroy.examen.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.usuario_item.view.*
import xyz.eliothmonroy.examen.R
import xyz.eliothmonroy.examen.model.data.Usuario
import xyz.eliothmonroy.examen.view.UsuarioDetalleActivity


class UsuariosAdapter(private val listaUsuarios: List<Usuario>): RecyclerView.Adapter<UsuariosAdapter.UsuarioHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.usuario_item, parent, false)
        return UsuarioHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: UsuarioHolder, position: Int) {
        holder.bindView(listaUsuarios[position])
    }

    override fun getItemCount(): Int {
        return listaUsuarios.size
    }

    class UsuarioHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        fun bindView(usuario:Usuario){
            itemView.usuarioNombre.text=itemView.context.getString(R.string.placeholder_usuario_nombre,usuario.nombre)
            itemView.usuarioCorreo.text=itemView.context.getString(R.string.placeholder_usuario_correo,usuario.correo)
            itemView.usuarioTelefono.text=itemView.context.getString(R.string.placeholder_usuario_telefono,usuario.telefono)
            itemView.setOnClickListener {
                val intent= Intent(itemView.context,
                    UsuarioDetalleActivity::class.java)
                intent.putParcelableArrayListExtra("Cuentas",usuario.cuentas)
                intent.putExtra("Nombre",usuario.nombre)
                itemView.context.startActivity(intent)
            }
        }
    }

}