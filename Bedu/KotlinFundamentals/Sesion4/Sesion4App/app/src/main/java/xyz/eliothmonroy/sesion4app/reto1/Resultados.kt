package xyz.eliothmonroy.sesion4app.reto1

object Resultados {
    var numMayor=0.0
    var numMenor=0.0
    var sumaDiagonal=0.0

    var list= listOf<List<Double>>()

    fun obtenerNumMayor(){
        numMayor=list[0][0]
        list.forEach {
            if(numMayor<it.max()?:0.0){
                numMayor=it.max()!!
            }
        }
    }
    fun obtenerNumMenor(){
        numMenor=list[0][0]
        list.forEach {
            if(numMenor>it.min()?:0.0){
                numMenor=it.min()!!
            }
        }
    }
    fun obtenerSumaDiagonal(){
        for (i in list.indices){
            for (j in list[0].indices){
                if(i==j){
                    sumaDiagonal+=list[i][j]
                }
            }
        }
    }
}