//If-else de forma tradicional
fun esMayor(data:Int):Boolean{
    if(data>0){
        println("$data")
        return true
    }else{
        println("$data")
        return false
    }
}
//If simplificado para una función que retorna un valor, usamos un igual en lugar de los corchetes y no usamos la palabra reservada return
fun esMayor2(data:Int):Boolean=
    if(data>0){
        println("$data")
        true
    }else{
        println("$data")
        false
    }

//Todos los objetos heredan de Any en Kotlin
fun defineTipo(data:Any){
    if(data is String){
        println(data.length)
    }else if (data is Int){
        println("${data*2}")
    }else if (data is Persona2){
        println(data.name)
    }
}

fun main(args: Array<String>) {
    val a=esMayor2(10)
    println("$a")
    defineTipo("Hola")
    defineTipo(10)
    defineTipo(Persona2("Elioth",22,"Cafés"))
}