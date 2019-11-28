fun main() {
    val fila1=listOf(1,2,3)
    val fila2=listOf(4,5,6)
    val matriz= listOf(fila1,fila2)
    matriz.forEach {
        it.forEach { it1 ->
            print("Item: $it1\t")
        }
        println()
    }
}