package me.arwan.model

data class ApiResponse<T>(
    val status: String,
    val data: T? = null,
    val error: String? = null
)