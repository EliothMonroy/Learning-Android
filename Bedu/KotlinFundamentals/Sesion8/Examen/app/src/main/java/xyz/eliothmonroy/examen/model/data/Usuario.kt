package xyz.eliothmonroy.examen.model.data

import com.google.gson.annotations.SerializedName
import java.util.ArrayList

data class Usuario (

	@SerializedName("correo") val correo : String,
	@SerializedName("nombre") val nombre : String,
	@SerializedName("telefono") val telefono : String,
	@SerializedName("cuentas") val cuentas : ArrayList<Cuentas>
)
