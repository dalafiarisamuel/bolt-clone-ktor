package com.devtamuno.data

import kotlinx.serialization.Serializable

@Serializable
data class TripHistory(
    val id: Long,
    val address: String,
    val amount: Double,
    val driverName: String,
    val tripStatus: String,
    val createdAt: String
)
