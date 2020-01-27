package xyz.eliothmonroy.retrofitdemo.reto1.data

import com.google.gson.annotations.SerializedName

data class Normalized (

	@SerializedName("from") val from : String,
	@SerializedName("to") val to : String
)