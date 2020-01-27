package xyz.eliothmonroy.sesion5piloto.ui.detail


import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_detail.*

import xyz.eliothmonroy.sesion5piloto.R

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment :Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button.setOnClickListener {
            //Inflate the dialog with custom view
            val mDialogView = LayoutInflater.from(activity).inflate(R.layout.dialog, null)
            //AlertDialogBuilder
            AlertDialog.Builder(activity)
                .setView(mDialogView)
                .setTitle("Comprar").show()
        }
    }




}
