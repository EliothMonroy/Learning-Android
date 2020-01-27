package xyz.eliothmonroy.sesion7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.PopupMenu
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), PopupMenu.OnMenuItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerForContextMenu(textView)
        textView2.setOnClickListener {
            showMenu(it)
        }
        showBottomSheetDialogFragment()
    }

    private fun showBottomSheetDialogFragment() {
        val bottomSheetFragment = ModalBottomSheet()
        bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_global,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.help -> {
                Toast.makeText(this, "Ayuda", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.logout->{
                Toast.makeText(this, "Cerrar sesión", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo ) {
        //super.onCreateContextMenu(menu, v, menuInfo)
        //ContextMenuInfo es null cuando solo hay un elemento
        menuInflater.inflate(R.menu.menu_contextual, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        //val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        return when (item.itemId) {
            R.id.editar -> {
                Toast.makeText(this, "Editar elemento", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.borrar -> {
                Toast.makeText(this, "Borrar elemento", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun showMenu(v: View) {
        PopupMenu(this, v).apply {
            // MainActivity implements OnMenuItemClickListener
            setOnMenuItemClickListener(this@MainActivity)
            inflate(R.menu.menu_emergente)
            show()
        }
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.color -> {
                Toast.makeText(this, "Cambiar color", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.letra -> {
                Toast.makeText(this, "Cambiar letra", Toast.LENGTH_SHORT).show()
                true
            }
            else -> false
        }
    }
}
