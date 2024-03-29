package com.devtamuno.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val email: String,
    @SerialName("phone_number")
    val phoneNumber: String,
    @SerialName("is_email_verified")
    val isEmailVerified: Boolean
)
