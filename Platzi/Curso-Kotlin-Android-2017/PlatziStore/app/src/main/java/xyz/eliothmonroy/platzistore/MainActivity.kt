package xyz.eliothmonroy.platzistore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerViewLanding.layoutManager=GridLayoutManager(this,2)
        val itemsShop=(1..20).map {
            ItemLanding("Título $it","Descipción ${it}",200.00f+it)
        }
        val adapter=AdapterLanding(itemsShop)
        recyclerViewLanding.adapter=adapter
    }
}
