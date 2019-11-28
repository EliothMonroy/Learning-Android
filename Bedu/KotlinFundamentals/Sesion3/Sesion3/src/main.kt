fun main() {
    val cuadrado=Cuadrado(5.0)
    val cArea=cuadrado.calcularArea()
    val cPerimetro=cuadrado.calcularPerimetro()

    val cuadrado2:Figura=Cuadrado(6.0) //Polimorfismo

    val circulo=Circulo(10.0)
    val cirArea=circulo.calcularArea()
    val cirPerimetro=circulo.calcularPerimetro()

    //println("Area cuadrado: $cArea")
    //println("Perímetro cuadrado: $cPerimetro")

    //println("Area circulo: ${"%.4f".format(cirArea)}")
    //println("Perímetro circulo: $cirPerimetro")

    val triangulo=Triangulo(10.0,10.0,10.0,10.0)
    val aTriangulo=triangulo.calcularArea()
    val pTriangulo=triangulo.calcularPerimetro()

    println("Area triangulo: ${"%.2f".format(aTriangulo)}")
    println("Perímetro circulo: ${"%.2f".format(pTriangulo)}")

    val trapecio=Trapecio(10.0,10.0,10.0,10.0,10.0)
    val aTrapecio=trapecio.calcularArea()
    val pTrapecio=trapecio.calcularPerimetro()

    println("Area trapecio: ${"%.2f".format(aTrapecio)}")
    println("Perímetro trapecio: ${"%.2f".format(pTrapecio)}")

}