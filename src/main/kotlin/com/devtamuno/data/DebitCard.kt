package com.devtamuno.data

import kotlinx.serialization.Serializable

@Serializable
data class DebitCard(
    val id: Long,
    val pan: String,
    val expiry: String,
    val cardHolderName: String,
    val isDefault: Boolean
)
