fun hello():Unit{ //Todas las funciones regresan Unit ->void
    println("Hola a todos")
}

fun suma(a:Int,b:Int){
    println("La suma de $a y $b es igual a ${a+b}")
}

//fun max(a:Int, b:Int): Int {
//    return if (a>b)
//        a
//    else
//        b
//}

fun max(a:Int, b:Int)=if (a>b) a else b //Otra forma de hacer la función de arriba

fun main(args:Array<String>) {
    suma(4,8)
    println(max(4,9))
}