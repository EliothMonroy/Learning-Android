import kotlin.math.pow

class Circulo(val radio:Double):Figura() {
    override fun calcularArea(): Double {
        return Math.PI.times(radio.pow(2))
    }

    override fun calcularPerimetro(): Double {
        return Math.PI.times(radio.times(2))
    }

}