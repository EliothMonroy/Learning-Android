fun main() {
    val arrA= intArrayOf(1,2,3,4,6) //Con está forma, indicamos los valores del arreglo directamente
    val listA= arrayListOf<String>("a","b","c") //También podemos crearlos así
    val num=arrA[0]//así podemos usar un valor
    val b=arrA.get(0)//Es equivalente a esto
    arrA[1]=10 //así podemos asignar valores
    val mutableList= mutableListOf<String>("a","b","c") //Así podemos crear una lista mutable (se pueden agregar valores)
    mutableList.add("d")//Así agregamos valores
    mutableList.add(2,"e")//Así podemos especificr la posición

    //Maps
    val mapa= mutableMapOf<String,Int>(Pair("a",30)) //Así podemos crear mapas
    mapa.put("b",40)//Así podemos agregar valores al mapa

    val arr = IntArray(5) { it * 1 }
    val arr2=IntArray(5)
    val emptyStringArray=mutableListOf<String>()
    emptyStringArray.add("a")
    println(arr.javaClass.kotlin.qualifiedName)//IntArray
    val array= listOf(1,2,3)
    println(array.javaClass.kotlin.qualifiedName)//Arrays.ArrayList
    val array2= arrayListOf(1,2,3)
    println(array2.javaClass.kotlin.qualifiedName)//ArrayList
}