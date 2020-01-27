fun main() {
    var set = mutableSetOf<Int>()
    set.add(0)
    set.remove(0)
    val list= mutableListOf<Int>()
    list.add(0)
    list.removeAt(0)
    list.add(1)
    println(list)

    val mapa = mutableMapOf("1" to 29, "2" to 40)
    mapa.put("3",50)
    mapa["4"] = 50
    mapa.remove("1")
    println(mapa)

}