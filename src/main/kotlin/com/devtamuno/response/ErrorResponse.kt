package com.devtamuno.response

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val success: Boolean,
    val statusCode: Int,
    val message: String,
)
