package com.devtamuno.data

import kotlinx.serialization.Serializable

@Serializable
data class RecentLocationResponse(val list: List<RecentLocation>)

@Serializable
data class QueryRecentLocation(val location: RecentLocation)
