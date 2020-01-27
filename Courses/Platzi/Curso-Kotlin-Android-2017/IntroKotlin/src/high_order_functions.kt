fun suma(num1:Int, num2:Int, func:()->Unit){ //La función de suma es una función de orden mayor debido a que recibe una función como parámetro,
                                             // también puede ser porque regresa una función como prarámetro
    println("Suma: ${num1+num2}")
    func() //La posición donde invocamos a la función que recibimos como parámetro se ejecuta en el orden en que la mandemos a llamar
}

fun String.funcion(func:(String)->Unit){
    //Cualquier código
    func(this) //Pasamos el objeto string a la función
    println(this.length)
}

fun String.funcion2(func:String.()->Unit){//Hacemos que la función que estamos recibiendo se vuelva parte de string también

    this.func() //Llamamos a la función
}

fun main() {
    suma(1,2){
        println("Función de orden mayor")
    }

    "Hola".funcion {
        //Con it ahora podemos obtener el string enviado desde func()
        println(it.reversed())
    }

    "Hola".funcion2 {
        //Con it ahora podemos obtener el string enviado desde func()
        println(this.reversed())
        println(length)
    }

}