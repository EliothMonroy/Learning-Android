package xyz.eliothmonroy.sesion8.reto1


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import xyz.eliothmonroy.sesion8.R

/**
 * A simple [Fragment] subclass.
 */
class SongsFragments : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_songs_fragments, container, false)
    }


}
