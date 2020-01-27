inline fun String.filter(predicate:(Char)->Boolean):String{ //Permite reducir el código cuando el programa es convertido a java
    return filterTo(StringBuilder(),predicate).toString()
}

fun main() {
    val a="Hola".filter {
        it=='H'
    }
    println(a)
}