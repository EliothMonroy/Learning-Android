class Cuadrado(val lado:Double): Figura() {
    override fun calcularArea(): Double {
        return lado.times(lado)
    }

    override fun calcularPerimetro(): Double {
        return lado.times(4)
    }

}