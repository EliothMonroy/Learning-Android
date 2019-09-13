fun type(dato:Any){
    when(dato){
        is String->{//Bloque con varias líneas
            println(dato.length)
        }
        is Int-> println(dato*dato) //Si solo es una línea podemos omitir las llaves
        is Boolean ->{
            println("Es un booleano")
            println(dato)
        }
        is Persona2->println(dato.name+" "+dato.edad)
        else -> println("No se que objeto es")
    }
}

fun whoIs(persona:Persona2)=
    when(persona.name){
        "Mabel","Elioth"->"Puedes abrir"
        else->"No abrir"
    }
//Podemos definir rangos de la siguiente forma
fun rango(numero:Int){
    when(numero){
        in 1..10-> println("Este es un número entre 1 y 10")
        in 10..100 -> println("Este es un número entre 10 y 100")
        else-> println("Este es un número fuera de los rangos")
    }
}

fun main() {
    type(9)
    type("9")
    type(true)
    type(Persona2("Elioth",22,"Cafe"))
    println(whoIs(Persona2("Elioth",22,"Cafe")))
    rango(1)
    rango(100)
    rango(1000)
}