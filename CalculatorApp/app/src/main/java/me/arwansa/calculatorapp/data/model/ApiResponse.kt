package me.arwansa.calculatorapp.data.model

import com.google.gson.annotations.SerializedName

data class ApiResponse<T>(
    @SerializedName("status")
    val status: String,

    @SerializedName("data")
    val data: T? = null,

    @SerializedName("error")
    val error: String? = null
)