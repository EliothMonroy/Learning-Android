package xyz.eliothmonroy.retrofitdemo.reto1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    /**
    * @desc Función que consulta el api web enviando como parámetro el texto ingresado por el usuario
    * y así mostrar el resultado de la consulta
    * @param value : El texto ingresado por el usuario para la búsqueda
    * */
    private fun obtenerResultadosApi(value:String) {
        val resultado=apiWiki.getValueApiWiki(titles=value)
        resultado.enqueue(object : Callback<Any> {
                override fun onFailure(call: Call<Any>, t: Throwable) {
                    Toast.makeText(this@HomeActivity,"Error: $t", Toast.LENGTH_SHORT).show()
                }
                override fun onResponse(call: Call<Any>, response: Response<Any>) {
                    if (response.isSuccessful){
                        //val respuesta=response.body() ?: "Hola"
                        response.body()?.let {
                            val objeto=JSONObject(Gson().toJson(it))//This method serializes the specified object into its equivalent Json representation.
                            val query=JSONObject(objeto["query"].toString())
                            val pages=JSONObject(query["pages"].toString())
                            val clave=pages.keys().next()
                            mostrarResultado(pages,clave,value)
                        } ?: run {
                            Toast.makeText(this@HomeActivity,"No se encontraron resultados para el término: $value, por favor vuelve a intentar en unos momentos", Toast.LENGTH_LONG).show()
                        }
                    }else{
                        Toast.makeText(this@HomeActivity,"No se encontraron resultados para el término: $value", Toast.LENGTH_LONG).show()
                    }
                }
            }
        )
    }

    /**
     * @desc Función que valida el resultado obtenido para determinar lo que se debe mostrar en pantalla
     * @param pages : El objeto json que contiene la respuesta a la consulta que se realizo al api
     * @param clave : El identificador del articulo que le corresponde al término buscado en el api
     * @param value : El texto ingresado por el usuario para la búsqueda
     * */
    private fun mostrarResultado(pages:JSONObject,clave:String,value: String){
        if (clave.toInt()>0){
            val wrapper=JSONObject(pages[clave].toString())
            if(wrapper["extract"].toString()!=""){
                webViewWiki.loadData(wrapper["extract"].toString(),"text/html","UTF-8")
            }else{
                Toast.makeText(this@HomeActivity,"No se encontro información sobre el término: $value", Toast.LENGTH_LONG).show()
            }
        }else{
            Toast.makeText(this@HomeActivity,"El término: $value, no se encuentra en la base de datos de wikipedia", Toast.LENGTH_LONG).show()
        }
    }

}
