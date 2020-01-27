class Triangulo(val base:Double, val lado2:Double, val lado3: Double, val altura:Double):Figura() {
    override fun calcularArea(): Double {
        return base.times(altura).div(2)
    }
    override fun calcularPerimetro(): Double {
        return base.plus(lado2).plus(lado3)
    }

}