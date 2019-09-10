fun saludar(saludo:String="Hola por default"){
    println(saludo)
}

fun test (edad: Int=0, esAdulto: Boolean=false): Unit {
    //nothing
}

data class P1(val a:Int=0)

fun main(args: Array<String>) {
    saludar("Hola a todos")
    saludar()
    test(esAdulto = true,edad = 30)
    val p=P1(10)
    val p2=P1()//Se usa el default value
}