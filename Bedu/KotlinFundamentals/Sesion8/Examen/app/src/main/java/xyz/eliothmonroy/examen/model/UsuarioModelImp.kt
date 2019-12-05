package xyz.eliothmonroy.examen.model

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.eliothmonroy.examen.model.api.API
import xyz.eliothmonroy.examen.model.data.Usuario
import xyz.eliothmonroy.examen.presenter.MainPresenter
import xyz.eliothmonroy.examen.utils.RetrofitUtils

class UsuarioModelImp(val mainPresenter: MainPresenter):UsuarioModel {

    private val retrofit= RetrofitUtils.getRetofitInstance()
    private val api=retrofit.create(API::class.java)

    override fun obtenerUsuarios() {
        val resultado=api.getUsuarios()
        resultado.enqueue(object : Callback<List<Usuario>> {
            override fun onFailure(call: Call<List<Usuario>>, t: Throwable) {
                mainPresenter.falloConexion("La conexión ha fallado")
            }
            override fun onResponse(call: Call<List<Usuario>>, response: Response<List<Usuario>>) {
                if (response.isSuccessful){
                    response.body()?.let {
                        val listaUsuarios:List<Usuario> =it
                        mainPresenter.mostrarUsuarios(listaUsuarios)
                    } ?: run {
                        mainPresenter.noHayUsuarios("No se encontraron resultados")
                    }
                }else{
                    mainPresenter.noHayUsuarios("No se encontraron resultados")
                }
            }
        })
    }
}