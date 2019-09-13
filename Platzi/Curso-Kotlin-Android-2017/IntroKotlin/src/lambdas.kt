//La estrucutra de un lambda es la siguiente;
// val name_lambda: (Type) -> Return type -> {assign -> process}
val sum:(Int,Int) -> Int = {x,y ->x+y}

//En caso de que el lambda solo reciba un parámetro podemos usar it
val minCuatro: (String) -> Boolean = {it.length>4 }

data class Carro(val color:String, val llantas:Int, val puertas:Int)

fun main() {
    println(sum(1,2))
    println(minCuatro("cuatro"))

    val carro:Carro?=Carro("Rojo",4,4)
    carro?.let {
        //Instrucciones en nuevo scope
        it.puertas
        with(it){
            puertas
            color
            llantas
        }
    }

    with(carro){
        this?.puertas
    }

}