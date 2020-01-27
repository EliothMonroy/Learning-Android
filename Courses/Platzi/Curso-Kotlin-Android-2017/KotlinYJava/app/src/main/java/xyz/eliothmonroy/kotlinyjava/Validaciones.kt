package xyz.eliothmonroy.kotlinyjava

import android.widget.EditText

/*
object Validaciones { //Objeto singleton
    @JvmStatic //Le indicamos a la jvm que este método será estatico, para no tener que usar Instance en el código Java
    fun EditText.passwordValida():Boolean{
        //Cadena no vacía y más de 6 caracteres
        return this.text.toString().isNotEmpty() && this.text.toString().length.compareTo(6)==1 //Con compareTo podemos verificar el número,
        // Si es 1 significa que el número es mayor, si es 0 es que son iguales, si es -1 es que es menor

    }
}*/

fun EditText.passwordValida():Boolean{
    //Cadena no vacía y más de 6 caracteres
    return this.text.toString().isNotEmpty() && this.text.toString().length.compareTo(6)==1 //Con compareTo podemos verificar el número,
    // Si es 1 significa que el número es mayor, si es 0 es que son iguales, si es -1 es que es menor

}