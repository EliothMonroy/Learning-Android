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
class Fragment2 : Fragment() {

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        Log.d("MY_FRAGMENT2","onAttach ejecutándose")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MY_FRAGMENT2","onCreate ejecutándose")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("MY_FRAGMENT2","onCreateView ejecutándose")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment2, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("MY_FRAGMENT2","onActivityCreated ejecutándose")
    }

    override fun onStart(){
        super.onStart()
        Log.d("MY_FRAGMENT2","onStart ejecutándose")
    }

    override fun onResume(){
        super.onResume()
        Log.d("MY_FRAGMENT2","onResume ejecutándose")
    }

    override fun onPause(){
        super.onPause()
        Log.d("MY_FRAGMENT2","onPause ejecutándose")
    }

    override fun onStop(){
        super.onStop()
        Log.d("MY_FRAGMENT2","onStop ejecutándose")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("MY_FRAGMENT2","onDestroyView ejecutándose")
    }
    override fun onDestroy(){
        super.onDestroy()
        Log.d("MY_FRAGMENT2","onDestroy ejecutándose")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("MY_FRAGMENT2","onDetach ejecutándose")
    }

}
