package xyz.eliothmonroy.sesion4app

import android.util.Log

class MiObjetoDos (var nombre:String) {
    init {
        Log.d("MiObjetoDos","Init de la clase MiObjetoDos")
        cont++
    }

    companion object{
        var cont=0
        var nombreGlobal="Elioth"
        init {
            Log.d("MiObjetoDos","Log del companion")
        }

        fun saludar(name: String):MiObjetoDos{
            return MiObjetoDos(name)
        }

    }

}