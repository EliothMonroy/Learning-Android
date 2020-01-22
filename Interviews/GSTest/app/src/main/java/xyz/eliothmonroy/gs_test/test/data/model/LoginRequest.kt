package xyz.eliothmonroy.gs_test.test.data.model

import com.google.gson.annotations.SerializedName

data class LoginRequest (
    @SerializedName("correo")
    val email: String,
    @SerializedName("contrasena")
    val password: String
)