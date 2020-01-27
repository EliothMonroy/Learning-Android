package xyz.eliothmonroy.examen.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cuenta_item.view.*
import xyz.eliothmonroy.examen.R
import xyz.eliothmonroy.examen.model.data.Cuentas

class CuentasAdapter(val listaCuentas:List<Cuentas>): RecyclerView.Adapter<CuentasAdapter.CuentaHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CuentaHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cuenta_item, parent, false)
        return CuentaHolder(view)
    }

    override fun getItemCount(): Int {
        return listaCuentas.size
    }

    override fun onBindViewHolder(holder: CuentaHolder, position: Int) {
        holder.bindView(listaCuentas[position])
    }

    class CuentaHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bindView(cuentas:Cuentas){
            itemView.cuentaNumero.text=itemView.context.getString(R.string.placeholder_cuenta_numero,cuentas.numero)
            itemView.cuentaSaldo.text=itemView.context.getString(R.string.placeholder_cuenta_saldo,cuentas.saldo)
        }
    }

}