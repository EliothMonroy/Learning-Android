fun main(args:Array<String>) {
    val srt="Una cadena de String normal"
    val nombre="Mabel"
    val edad=25

    val sentencia="Ella es "+nombre+" y tiene "+edad+" años" //Forma tradicional
    val sentencia2= "Ella es $nombre y tiene $edad años" //Forma kotlin

    println(sentencia2)

    val cumpl="$nombre cumple ${edad+1} años" //Podemos hacer operaciones con variables antes de convertirlas a string

    println(cumpl)

    val text="""
        |String largo
        """.trimMargin()
    println(text)
}