fun main() {
    val listaNumeros:IntArray= intArrayOf(1,2,3,4,5)
    for (i in listaNumeros.indices){//Obtenemos los indices en lugar de los valores almacenados en el arreglo
        println(listaNumeros[i])
    }
    val listaFrutas= listOf("manzana","piña","platano","uvas")
    for (i in listaFrutas){
        println(i)
    }
    //while
    var x=3
    while (x>0){
        println(x)
        x--
    }
    var suma=0
    var input:String?=""//La entrada puede ser nula
    do {
        println("Ingresa un número")
        input= readLine()
        suma+=input?.toInt()?:0
    }while(input!="0")
    println("La suma es: $suma")
}