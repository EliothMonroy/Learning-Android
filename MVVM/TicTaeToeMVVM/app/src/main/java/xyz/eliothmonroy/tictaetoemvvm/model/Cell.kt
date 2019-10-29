package xyz.eliothmonroy.tictaetoemvvm.model

data class Cell(var player: Player) {
    fun isEmpty():Boolean{
        when (player){
            null->return true
            else->return false
        }
    }
}