data class Auto(var marca:String, private var puertas:Int){//Nos da acceso a setters y getters si se usa var en lugar de val, si no solo getters
    var numPuertas=puertas
    get() {
        return if (field>0) field else 1
    }
}

data class Persona2(var name:String, val edad:Int, val colorOjos:String)

fun main(args: Array<String>) {
    val auto=Auto("Ford",0)
    val marca=auto.marca
    println("Marca: $marca")
    println(auto.numPuertas)
    auto.marca="Chevr"
    println(auto.hashCode())
    println(auto.toString())

    val mabel=Persona2("Mabel",4,"Cafes")

    val marla=mabel.copy("Marla") //Podemos copiar el objeto

    println(mabel)
    println(marla)

    val darla=mabel
    darla.name="Darla"

    println(mabel)
    println(marla)
    println(darla)

}