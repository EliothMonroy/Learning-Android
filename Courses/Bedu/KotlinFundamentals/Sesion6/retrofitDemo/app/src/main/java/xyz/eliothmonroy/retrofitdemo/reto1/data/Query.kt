package xyz.eliothmonroy.retrofitdemo.reto1.data
import com.google.gson.annotations.SerializedName

data class Query (

	@SerializedName("normalized") val normalized : List<Normalized>,
	@SerializedName("pages") val pages : Pages
)