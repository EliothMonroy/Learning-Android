data class Familiar(val name:String="Elioth",val age:Int=24,val hairColor:String="Negro")

data class Result(val result:Int, val status:Boolean)

fun calcular(a:Int, b:Int):Result{
    if (a*2>b){
        return Result(a*2,true)
    }else{
        return Result(a*2,false)
    }
}

fun main() {
    val familiar=Familiar()
    //Para obtener los valores del objeto tendríamos que hacer algo así tradicionalmente
    val name=familiar.name
    val age=familiar.age
    val hairColor=familiar.hairColor

    //Pero podemos hacerlo diferente con un destructuring declaration
    //Asignamos todas las propiedades del objeto a tres variables
    val (name1, age1, hairColor1)=Familiar()
    //Podemos asignar solamente algunas variables indicando con un underscore las propiedades que no deseamos
    val(name2,_,hairColor2)=Familiar()
    println(name2)
    println(hairColor2)

    //También podemos usarlo en funciones que retornen objetos
    val (resultado,status)=calcular(2,3)
    println(resultado)
    println(status)
}