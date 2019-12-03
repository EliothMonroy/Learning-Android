package xyz.eliothmonroy.retrofitdemo.reto1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_home.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.eliothmonroy.retrofitdemo.R
import xyz.eliothmonroy.retrofitdemo.reto1.api.API
import xyz.eliothmonroy.retrofitdemo.reto1.api.RetrofitUtils

class HomeActivity : AppCompatActivity() {

    private val retrofit=RetrofitUtils.getRetofitInstance()
    private lateinit var apiWiki:API

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        apiWiki=retrofit.create(API::class.java)

        buscar.setOnClickListener {
            if(busqueda.text.toString()!=""){
                obtenerResultadosApi(busqueda.text.toString())
            }else{
                Toast.makeText(this@HomeActivity,"Por favor ingrese algún término", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun obtenerResultadosApi(value:String) {
        val resultado=apiWiki.getValueApiWiki("query","extracts","json","",value)
        resultado.enqueue(object : Callback<Any> {
                override fun onFailure(call: Call<Any>, t: Throwable) {
                    Toast.makeText(this@HomeActivity,"Error: $t", Toast.LENGTH_SHORT).show()
                }
                override fun onResponse(
                    call: Call<Any>,
                    response: Response<Any>
                ) {
                    if (response.isSuccessful){
                        response.body()?.let {
                            val objeto=JSONObject(Gson().toJson(response.body()))
                            val query=JSONObject(objeto["query"].toString())
                            val pages=JSONObject(query["pages"].toString())
                            val clave=pages.keys()
                            val wrapper=JSONObject(pages[clave.next()].toString())
                            Log.d("Home",wrapper.toString())
                            webViewWiki.loadData(wrapper["extract"].toString(),"text/html","UTF-8")
                        }
                    }
                }
            }
        )

    }
}
