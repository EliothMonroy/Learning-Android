fun main() {
    for (i in 1..4){ //Así podemos definir un rango, que solo funcioa con numeros de menor a mayor
        println(i)
    }
    for (i in 4 downTo 1){ //Así podemos definir un rango a la inversa, con el operador downTo (funcion infix)
        println(i)
    }
    for (i in 1..4 step 2){ //Usando step, podemos definir saltos que queramos en nuestro rango
        println(i)
    }
    for (i in 1 until 10){ //Así podemos definir un rango en el cual no se considere el número posterior a until
        println(i)
    }
    val esOnce=(1..12 step 2).last==11
    println(esOnce)
}