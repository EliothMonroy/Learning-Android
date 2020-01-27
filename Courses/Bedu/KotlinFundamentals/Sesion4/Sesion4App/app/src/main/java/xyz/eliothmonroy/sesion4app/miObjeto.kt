package xyz.eliothmonroy.sesion4app

import android.util.Log

object miObjeto {

    var URL_BASE="https://eliothmonroy.xyz"

    init {
        Log.d("miObjeto","Solo me ejecuto una vez")
    }

    fun saludar(){
        Log.d("miObjeto","Hola desde miObjeto")
    }

}