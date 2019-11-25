fun main(args: Array<String>) {

    // Declaracion de variables
    // Variable de asignacion inmediata
    val inmediata:Int=1

    // Variable de asignacion mutable
    var mutable:String


    // Variable con tipo de dato inferido
    val inferido="Hola"

    // Variable no inicializada
    var mutable2:String

    // Variable Changeable
    var change:String="Hola"
    change="Hola2"

    // Variable Unchangeable
    val unchange:Int=10
    //unchange=11

    // Operaciones

    val a = 10
    var b = 11

    println(a + b)
    println(a - b)
    println(a * b)
    println(a / b)
    println(a % b)
    println(a ..b)

    println(a.plus(b))
    println(a.minus(b))
    println(a.times(b))
    println(a.div(b))
    println(a.rem(b))
    println(a.rangeTo(b))

}