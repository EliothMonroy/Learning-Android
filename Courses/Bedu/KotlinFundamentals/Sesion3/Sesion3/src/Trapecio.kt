class Trapecio(val baseMayor:Double, val baseMenor:Double, val ladoIzquierdo:Double, val ladoDerecho: Double,val altura:Double):Figura() {
    override fun calcularArea(): Double {
        return baseMenor.plus(baseMayor).times(altura).div(2)
    }

    override fun calcularPerimetro(): Double {
        return baseMenor.plus(baseMayor).plus(ladoIzquierdo).plus(ladoDerecho)
    }

}