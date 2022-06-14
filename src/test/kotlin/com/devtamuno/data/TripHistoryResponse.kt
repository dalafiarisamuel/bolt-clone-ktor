package com.devtamuno.data

import kotlinx.serialization.Serializable

@Serializable
data class TripHistoryResponse(val list: List<TripHistory>)
