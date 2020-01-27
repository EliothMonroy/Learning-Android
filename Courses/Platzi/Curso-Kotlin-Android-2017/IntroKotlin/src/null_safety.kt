fun main() {
    var a:String="abc"
    //a=null //Esto no se puede
    var b:String?="abc"
    b=null //Esto se puede hacer
    val l1=a.length //Esto se puede hacer
    val l2=b?.length ?:0//Necesitamos incluir ? para poder realizar la operación. ...
    //usando elvis operator (?:) podemos indicar un valor que tomará por default la variable en caso de que "b" sea nulo
    println(l1)
    println(l2)

    val persona:Persona2?=Persona2("Elioth",22,"Cafés")
    val nombre=persona?.name
    //De la siguiente forma, podemos ejecutar un código en caso de que un objeto no sea nulo
    persona?.let {
        //código a ejecutar, solo se ejecuta si el objeto no es nulo
    }
    persona.let {
        //Este código siempre se ejecuta sin importar si es nulo o no
    }
}