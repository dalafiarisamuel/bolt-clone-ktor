package com.devtamuno.routes

import com.devtamuno.data.DummyData
import com.devtamuno.data.TripHistoryResponse
import com.devtamuno.plugins.configureRouting
import com.google.common.truth.Truth
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import org.junit.Test

@OptIn(ExperimentalSerializationApi::class)
class TripHistoryRouteTest {

    private val json = Json {
        ignoreUnknownKeys = true
    }


    @Test
    fun `test getTripHistories route, expecting OK status`() = testApplication {

        application {
            configureRouting()
        }

        client.get("/trip-histories").apply {
            Truth.assertThat(HttpStatusCode.OK).isEqualTo(status)
        }
    }


    @Test
    fun `test getTripHistories route, returns data list`() = testApplication {

        application {
            configureRouting()
        }

        client.get("/trip-histories").apply {

            val data: TripHistoryResponse = json.decodeFromStream(body())

            Truth.assertThat(HttpStatusCode.OK).isEqualTo(status)
            Truth.assertThat(data.list).isNotEmpty()
            Truth.assertThat(DummyData.DummyTripHistories.size).isEqualTo(data.list.size)
        }
    }
}