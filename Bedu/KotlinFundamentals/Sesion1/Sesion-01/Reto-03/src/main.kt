fun main(args: Array<String>) {
    val vocales = arrayOf("A", "E", "I", "O", "U")
    val letra = "A"
    val letraBuscada: String

    letraBuscada=if (letra in vocales) letra else "X"

    println("letraBusqueda: $letraBuscada")

    /*
     * Solucion Condicionales
     */


    val numeros: Array<Int> = arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
    val n1 = 4

    /*
     * Soluciones Ciclos
     */

    println("CON FOR")
    for (num in numeros){
        if (num==n1){
            println("Cuatro")
        }else{
            println("¿$num es número par?: ${(num%2)==0}")
        }
    }

    println("CON FOREACH")
    numeros.forEach {
        if (it==n1){
            println("Cuatro")
        }else{
            println("¿$it es número par?: ${(it%2)==0}")
        }
    }

}