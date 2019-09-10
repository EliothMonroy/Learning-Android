fun String.print(){ //Podemos extender (hacer nuevas funciones) a objetos que existen, inclusives los pertenecientes al core de Kotlin
    println(this) //This hace referencia al objeto que estamos extendiendo
}

fun Int.multiply(numM:Int): Int{
    return this * numM
}

fun main(args: Array<String>) {
    "Hola mi nombre es Elioth".print() //Llamamos a la función que definimos arriba
    "${10.multiply(5)}".print()
}