fun main(args: Array<String>) {

    /*
    * Tipos de datos
    */

    var byte:Byte=1
    var short:Short=10
    var int:Int=1000
    var long:Long=10000000000
    var string:String="Hola"

    println("Byte: $byte")
    println("Short: $short")
    println("Int: $int")
    println("Long: $long")
    println("String: $string")

    /*
    * Operadores
    */

    var A:Int = 5
    var B:Int = 10
    var numbers:Array<Int> = arrayOf(1, 2, 3, 4, 5)
    var yes:Boolean = true

    println("+A: ${A.unaryPlus()}")
    println("-B: ${B.unaryMinus()}")
    println("!yes: ${yes.not()}")
    println("A++: ${A.inc()}")
    println("B++: ${B.dec()}")
    println("A+B: ${A.plus(B)}")
    println("A-B: ${A.minus(B)}")
    println("A*B: ${A.times(B)}")
    println("A/B: ${A.div(B)}")
    println("A%B: ${A.rem(B)}")
    println("A..B: ${A.rangeTo(B)}")
    println("A in numbers: ${numbers.contains(A)}")
    println("A not in numbers: ${numbers.contains(A).not()}")
    println("numbers[0]: ${numbers[0]}")
    numbers[0]=A
    println("numbers[0]=A: ${numbers[0]}")
    println("A==B: ${A.equals(B)}")
    println("A!=B: ${A.equals(B).not()}")
    println("A>B: ${A.compareTo(B).equals(1)}")
    println("A<B: ${A.compareTo(B).equals(-1)}")
    println("A>=B: ${(0..1).contains(A.compareTo(B))}")
    println("A<=B: ${(-1..0).contains(A.compareTo(B))}")

}