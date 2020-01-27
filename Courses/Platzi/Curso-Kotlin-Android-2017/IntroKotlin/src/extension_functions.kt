fun String.print(){ //Podemos extender (hacer nuevas funciones) a objetos que existen, inclusives los pertenecientes al core de Kotlin
    println(this) //This hace referencia al objeto que estamos extendiendo
}

fun Int.multiply(numM:Int): Int{
    return this * numM
}

infix fun Int.multiply2(numM:Int):Int{ //Si definimos la función con el prefijo infix, indicamos que la función puede ser llamada como si fuera un operador (+.*, etc)
    return this*numM
}

fun <T> MutableList<T>.swap(index1:Int, index2:Int){//Podemos extender también objetps complejos, en este caso usamos T para indicar un objeto genérico
    val tmp:T=this[index1]
    this[index1]=this[index2]
    this[index2]=tmp
}

fun main(args: Array<String>) {
    "Hola mi nombre es Elioth".print() //Llamamos a la función que definimos arriba
    "${10.multiply(5)}".print()
    "${10 multiply2 6}".print()
    val listaNombres= mutableListOf("A","B","C")
    listaNombres.swap(0,2)
    listaNombres.toString().print()
}