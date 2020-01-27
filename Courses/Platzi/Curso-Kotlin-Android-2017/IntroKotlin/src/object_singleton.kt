import java.lang.NumberFormatException

object Validaciones{
    fun passwordValida(psw:String) = psw.isNotEmpty() && psw.length>10
    fun esNumero(dato:Any)= dato is Int

}

class ClaseUniversal{
    companion object{ //Companion object permite definir metodos dentro de una clase que pueden ser accesados sin instanciar la clase, es parecido a "static" en Java

        fun create():ClaseUniversal=ClaseUniversal()
    }
}
//En sí el companion object es un objeto por si solo de tipo singleton
class Car(val horsepowers: Int) {
    companion object Factory {
        val cars = mutableListOf<Car>()

        fun makeCar(horsepowers: Int): Car {
            val car = Car(horsepowers)
            cars.add(car)
            return car
        }
    }
}

fun data(){
    val claseUniversalData=ClaseUniversal.create()
    println(claseUniversalData)
}

fun main() {
    println("Ingresa una contraseña")
    val passw= readLine()!! //Indica que obtengamos un valor pase lo que pase, pero debemos evitar usarlo
    println(Validaciones.passwordValida(passw))
    println("Ingresa un número")
    try {
        val numero= readLine()?.toInt() ?: ""
        println(numero)
        println(Validaciones.esNumero(numero))
    }catch (e:NumberFormatException){
        e.printStackTrace()
    }

    val claseUniversal=ClaseUniversal.create()
    println(claseUniversal)
    data()

    val car = Car.makeCar(150)
    println(Car.Factory.cars.size)
}