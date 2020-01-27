package xyz.eliothmonroy.kotlinyjava

//De está forma indicamos que queremos que se genere el otro constructor (donde solo recibimos título, ahora tendríamos dos constructores)
data class PojoBasico @JvmOverloads constructor(val titulo:String, val desc:String="Vacío")