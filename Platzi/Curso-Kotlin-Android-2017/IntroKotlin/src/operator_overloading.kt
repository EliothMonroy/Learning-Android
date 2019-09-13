data class Tiempo(val years:Int, val days:Int)

operator fun Tiempo.plus(other:Tiempo):Tiempo{ //Con la palabra reservada operator podemos sobrecargar operadores
    return Tiempo(this.years+other.years,this.days+other.days)
}

data class Contador(var index:Int){
    operator fun inc():Contador{ //Sobrecargamos el operador dentro del mismo objeto para que solo este disponislbe aquí
        return Contador(index+1)
    }
}

fun main() {
    val tiempo1=Tiempo(2000,30)
    val tiempo2=Tiempo(100,2)
    val sumaDelTiempo=tiempo1+tiempo2
    println("Years: ${sumaDelTiempo.years}, days: ${sumaDelTiempo.days}")

    var contador=Contador(0)
    println(++contador)
}