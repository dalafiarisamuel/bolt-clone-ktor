package com.devtamuno.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DebitCard(
    val id: Long,
    val pan: String,
    val expiry: String,
    @SerialName("card_holder_name")
    val cardHolderName: String,
    @SerialName("is_default")
    val isDefault: Boolean
)
