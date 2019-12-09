package xyz.eliothmonroy.sesion3.reto2


import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import xyz.eliothmonroy.sesion3.R

/**
 * A simple [Fragment] subclass.
 */
class MyFragment : Fragment() {

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        Log.d("MY_FRAGMENT","onAttach ejecutándose")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MY_FRAGMENT","onCreate ejecutándose")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("MY_FRAGMENT","onCreateView ejecutándose")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("MY_FRAGMENT","onActivityCreated ejecutándose")
    }

    override fun onStart(){
        super.onStart()
        Log.d("MY_FRAGMENT","onStart ejecutándose")
    }

    override fun onResume(){
        super.onResume()
        Log.d("MY_FRAGMENT","onResume ejecutándose")
    }

    override fun onPause(){
        super.onPause()
        Log.d("MY_FRAGMENT","onPause ejecutándose")
    }

    override fun onStop(){
        super.onStop()
        Log.d("MY_FRAGMENT","onStop ejecutándose")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("MY_FRAGMENT","onDestroyView ejecutándose")
    }
    override fun onDestroy(){
        super.onDestroy()
        Log.d("MY_FRAGMENT","onDestroy ejecutándose")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("MY_FRAGMENT","onDetach ejecutándose")
    }
}
