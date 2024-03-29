package com.devtamuno.response

import kotlinx.serialization.Serializable

@Serializable
data class Response<out T>(
    val success: Boolean,
    val statusCode: Int,
    val message: String,
    val data: T? = null,
)
