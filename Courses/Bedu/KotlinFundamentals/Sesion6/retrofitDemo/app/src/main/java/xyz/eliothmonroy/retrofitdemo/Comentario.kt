package xyz.eliothmonroy.retrofitdemo

import com.google.gson.annotations.SerializedName

data class Comentario (
    @SerializedName("postId") val postId:Int,
    @SerializedName("id") val id:Int,
    @SerializedName("name") val name:String,
    @SerializedName("body") val body:String
)