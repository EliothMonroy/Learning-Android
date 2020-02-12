package xyz.eliothmonroy.restconsumetest.async.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.eliothmonroy.restconsumetest.async.data.model.Todo
import xyz.eliothmonroy.restconsumetest.async.data.service.AsyncService

class AsyncRepositoryImpl : AsyncRepository {

    private val asyncService=AsyncService.Builder().build()

    override fun getTodo(id: Int): LiveData<Todo> {

        val liveData = MutableLiveData<Todo>()

        asyncService.getTodo(id).enqueue(object: Callback<Todo> {
            override fun onResponse(call: Call<Todo>, response: Response<Todo>) {
                if (response.isSuccessful) {

                    // When data is available, populate LiveData
                    liveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<Todo>, t: Throwable) {
                t.printStackTrace()
            }
        })

        // Synchronously return LiveData
        // Its value will be available onResponse
        return liveData
    }
}