package com.devtamuno.data

import kotlinx.serialization.Serializable

@Serializable
data class Wallet(
    val balance: Double,
    val currency: String
)
