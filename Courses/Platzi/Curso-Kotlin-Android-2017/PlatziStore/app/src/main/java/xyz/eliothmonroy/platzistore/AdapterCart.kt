package xyz.eliothmonroy.platzistore

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

class AdapterCart(val data: List<ItemLanding>) : RecyclerView.Adapter<AdapterCart.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder =
        Holder(ItemList().createView(AnkoContext.Companion.create(parent.context,this,false)))

    override fun getItemCount(): Int =data.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindView(data[position])
    }

    class Holder(itemView: View):RecyclerView.ViewHolder(itemView){
        val txtViewTitle by lazy {
            itemView.find<TextView>(R.id.txtTitleItem2)
        }
        val txtViewDesc by lazy {
            itemView.find<TextView>(R.id.txtDescItem2)
        }
        val txtViewPrice by lazy {
            itemView.find<TextView>(R.id.txtPriceItem2)
        }
        fun bindView(itemLanding: ItemLanding){
            with(itemLanding){
                txtViewTitle.text=title
                txtViewDesc.text=desc
                txtViewPrice.text="$ ${String.format("%.2f",price)}"
            }
        }
    }
}