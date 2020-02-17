package xyz.eliothmonroy.testlibrary.domain


interface TodoInteractor {

    suspend fun getTodo(id:Int): xyz.eliothmonroy.testlibrary.data.model.Response
}