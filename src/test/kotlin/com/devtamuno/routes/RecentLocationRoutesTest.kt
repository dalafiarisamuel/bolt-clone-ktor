package com.devtamuno.routes

import com.devtamuno.data.DummyData
import com.devtamuno.data.ErrorResponse
import com.devtamuno.data.QueryRecentLocation
import com.devtamuno.data.RecentLocationResponse
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
class RecentLocationRoutesTest {


    private val json = Json {
        ignoreUnknownKeys = true
    }


    @Test
    fun `test getRecentLocations route expecting OK status`() = testApplication {

        client.get("/recent-locations").apply {
            Truth.assertThat(HttpStatusCode.OK).isEqualTo(status)
        }
    }


    @Test
    fun `test getRecentLocations route expecting list of recent locations`() = testApplication {
        application {
            configureRouting()
        }

        client.get("/recent-locations").apply {

            val data: RecentLocationResponse = json.decodeFromStream(body())

            Truth.assertThat(HttpStatusCode.OK).isEqualTo(status)
            Truth.assertThat(data.list).isNotEmpty()
            Truth.assertThat(data.list.size).isEqualTo(DummyData.DummyRecentLocations.size)
        }
    }

    @Test
    fun `test getRecentLocation route with missing query id parameter, returns error `() = testApplication {
        application {
            configureRouting()
        }

        client.get("/recent-locations?id=").apply {

            val error: ErrorResponse = json.decodeFromStream(body())

            Truth.assertThat(HttpStatusCode.BadRequest).isEqualTo(status)
            Truth.assertThat(error.error).isEqualTo("id must be an integer")
        }

    }

    @Test
    fun `test getRecentLocation route with wrong query data type, returns error`() = testApplication {
        application {
            configureRouting()
        }

        client.get("/recent-locations?id=abc").apply {

            val error: ErrorResponse = json.decodeFromStream(body())

            Truth.assertThat(HttpStatusCode.BadRequest).isEqualTo(status)
            Truth.assertThat(error.error).isEqualTo("id must be an integer")
        }
    }


    @Test
    fun `test getRecentLocation route with id not found in resource, returns error`() = testApplication {
        application {
            configureRouting()
        }

        val id = 1000

        client.get("/recent-locations?id=$id").apply {

            val error: ErrorResponse = json.decodeFromStream(body())

            Truth.assertThat(HttpStatusCode.BadRequest).isEqualTo(status)
            Truth.assertThat(error.error).isEqualTo("No recent location with id $id")
        }

    }

    @Test
    fun `test getRecentLocation with right query parameter, returns success`() = testApplication {
        application {
            configureRouting()
        }

        val id = 1

        client.get("/recent-locations?id=$id").apply {

            val data: QueryRecentLocation = json.decodeFromStream(body())

            Truth.assertThat(HttpStatusCode.OK).isEqualTo(status)
            Truth.assertThat(data.location).isIn(DummyData.DummyRecentLocations)
        }
    }

}