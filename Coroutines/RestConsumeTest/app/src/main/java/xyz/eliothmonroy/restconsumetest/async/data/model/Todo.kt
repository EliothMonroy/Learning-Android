package xyz.eliothmonroy.restconsumetest.async.data.model

import com.google.gson.annotations.SerializedName

data class Todo(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("title")
    val title: String = "",
    @SerializedName("completed")
    val completed: Boolean = false
)