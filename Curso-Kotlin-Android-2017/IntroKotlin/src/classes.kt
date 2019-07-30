class Ejemplo{
    fun hola(){
        println("Hola Kotlin")
    }
}

class Persona1 constructor(val nombre:String){ //Constructor

}

class Persona(val nombre: String){ //Constructor sin palabra reservada
    init { //Bloque que se ejecuta cuando sea crea la clase
        println("Nombre $nombre")
    }
    constructor(name:String, edad:Int):this(name){ //Le estamos pasando el nombre al constructor principal
        println("Nombre $nombre, edad $edad")
    }

    override fun toString(): String {
        return super.toString()
    }
}

//herencia

open class Base(val inicial:Int){ //Para que puedan heredad de ella
    open fun imprimeAlgo(){ //Se puede sobreescribir ahora
        println("Algo $inicial")
    }
}

class General(val d:Int):Base(d){
    override fun imprimeAlgo() {
        super.imprimeAlgo()
        val suma:Int=4+d
    }
}

fun main(args: Array<String>) {
    val persona=Persona("Pedro",30) //Instanciamos la clase
    println(persona.toString())
    val general=General(9)
    general.imprimeAlgo()
}