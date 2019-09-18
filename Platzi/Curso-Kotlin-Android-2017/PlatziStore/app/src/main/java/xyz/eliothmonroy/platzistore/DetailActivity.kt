package xyz.eliothmonroy.platzistore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_description.*
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.startActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val db=DbOpenHelper.getInstance(this)

        intent.extras?.let {
            val title=it.getString("title")
            val desc=it.getString("desc")
            val price=it.getFloat("price")
            txtDetailTitle.text=it.getString("title")
            txtDetailDesc.text=it.getString("desc")
            txtDetailPrice.text="$ ${String.format("%.2f",it.getFloat("price"))}"
            btnDetailBuy.setOnClickListener {
                db?.use {
                    val namePr="name" to title
                    val descPr="Desc" to desc
                    val pricePr="price" to price
                    insert("Productos",namePr,descPr,pricePr)
                }
                //Llamamos a la actividad del carrito con anko
                startActivity<ShopCartActivity>()
            }
        }
    }
}
