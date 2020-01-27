package xyz.eliothmonroy.retrofitdemo.reto1.data

import com.google.gson.annotations.SerializedName

data class Resultado (
	@SerializedName("batchcomplete") val batchcomplete : String,
	@SerializedName("warnings") val warnings : Warnings,
	@SerializedName("query") val query : Query
)