package xyz.eliothmonroy.platzistore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_shop_cart.*
import org.jetbrains.anko.db.select

class ShopCartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_cart)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        rcCart.layoutManager = layoutManager
        rcCart.setHasFixedSize(true)

        val items = arrayListOf<ItemLanding>()

        val db=DbOpenHelper.getInstance(this)
        db?.use {
            select("Productos").exec {
                Log.e("Columnas","${columnCount}")
                columnNames.map {
                    Log.e("Columna",it)
                }
                moveToFirst()
                do {
                    items.add(ItemLanding(getString(1), getString(2), getDouble(3).toFloat()))
                }while (moveToNext())
            }
        }
        rcCart.adapter = AdapterCart(items)
    }
}
