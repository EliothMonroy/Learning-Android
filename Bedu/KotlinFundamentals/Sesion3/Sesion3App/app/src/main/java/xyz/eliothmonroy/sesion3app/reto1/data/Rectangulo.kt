package xyz.eliothmonroy.sesion3app.reto1.data

import Figura

class Rectangulo(val ladoMenor:Double, val ladoMayor:Double): Figura() {
    override fun calcularArea(): Double {
        return ladoMenor.times(ladoMayor)
    }

    override fun calcularPerimetro(): Double {
        return ladoMenor.times(2).plus(ladoMayor.times(2))
    }

}