package xyz.eliothmonroy.retrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val URL_Base="https://jsonplaceholder.typicode.com/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofitComentario=Retrofit.Builder().baseUrl(URL_Base).addConverterFactory(GsonConverterFactory.create()).build()

        val apiWeb=retrofitComentario.create(ApiWeb::class.java)

        val callRespuesta=apiWeb.recuperaComentarios("comments")

        callRespuesta.enqueue(object :Callback<List<Comentario>>{
            override fun onFailure(call: Call<List<Comentario>>, t: Throwable) {
                Toast.makeText(this@MainActivity,"Error: $t",Toast.LENGTH_SHORT).show()
            }
            override fun onResponse(
                call: Call<List<Comentario>>,
                response: Response<List<Comentario>>
            ) {
                if (response.isSuccessful){
                    response.body()?.forEach {
                        Log.d("TEST",it.toString())
                    }
                }
            }
        })
    }
}
