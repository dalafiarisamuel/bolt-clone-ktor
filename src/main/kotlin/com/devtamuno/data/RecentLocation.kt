package com.devtamuno.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecentLocation(
    val id: Long,
    val label: String,
    val address: String,
    @SerialName("is_work_address")
    val isWorkAddress: Boolean,
    @SerialName("is_house_address")
    val isHouseAddress: Boolean
)