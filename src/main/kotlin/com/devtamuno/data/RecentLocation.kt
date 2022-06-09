package com.devtamuno.data

import kotlinx.serialization.Serializable

@Serializable
data class RecentLocation(
    val id: Long,
    val label: String,
    val address: String,
    val isWorkAddress: Boolean,
    val isHouseAddress: Boolean
)