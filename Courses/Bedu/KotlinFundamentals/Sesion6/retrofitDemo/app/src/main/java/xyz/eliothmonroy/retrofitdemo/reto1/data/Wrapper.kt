package xyz.eliothmonroy.retrofitdemo.reto1.data

import com.google.gson.annotations.SerializedName

data class Wrapper (

	@SerializedName("pageid") val pageid : Int,
	@SerializedName("ns") val ns : Int,
	@SerializedName("title") val title : String,
	@SerializedName("extract") val extract : String
)