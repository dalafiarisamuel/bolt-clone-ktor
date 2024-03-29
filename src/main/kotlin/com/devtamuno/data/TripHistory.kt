package com.devtamuno.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TripHistory(
    val id: Long,
    val address: String,
    val amount: Double,
    @SerialName("driver_name")
    val driverName: String,
    @SerialName("trip_status")
    val tripStatus: String,
    @SerialName("created_at")
    val createdAt: String
)
