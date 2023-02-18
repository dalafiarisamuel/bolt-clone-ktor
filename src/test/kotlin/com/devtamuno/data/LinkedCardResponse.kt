package com.devtamuno.data

import kotlinx.serialization.Serializable

@Serializable
data class LinkedCardResponse(val list: List<DebitCard>)
