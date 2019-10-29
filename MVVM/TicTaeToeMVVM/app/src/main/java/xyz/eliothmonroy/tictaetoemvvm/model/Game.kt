package xyz.eliothmonroy.tictaetoemvvm.model

class Game(val player1:String, val player2:String) {

    init {

    }

    companion object {
        private val TAG=this::class.qualifiedName
        const val BOARD_SIZE=3
    }
}