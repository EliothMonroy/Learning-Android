package xyz.eliothmonroy.gs_test.test.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("codigoOperacion")
    val mCodeOperation: Int,
    @SerializedName("mensaje")
    val mDescription: String,
    @SerializedName("estatus")
    val status: String,
    @SerializedName("token")
    val token: String,
    @SerializedName("idCliente")
    val idClient: String
)