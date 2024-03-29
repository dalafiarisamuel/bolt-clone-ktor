package com.devtamuno.routes

import com.devtamuno.data.DummyData
import com.devtamuno.data.RecentLocation
import com.devtamuno.response.Response
import com.devtamuno.plugins.configureRouting
import com.devtamuno.response.ErrorResponse
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
internal class RecentLocationRouteKtTest {


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

            val response: Response<List<RecentLocation>> = json.decodeFromStream(body())

            Truth.assertThat(HttpStatusCode.OK).isEqualTo(status)
            Truth.assertThat(response.data).isNotEmpty()
            Truth.assertThat(response.data?.size).isEqualTo(DummyData.DummyRecentLocations.size)
        }
    }

    @Test
    fun `test getRecentLocation route with missing query id parameter, returns error `() = testApplication {
        application {
            configureRouting()
        }

        client.get("/recent-location?id=").apply {

            val error: ErrorResponse = json.decodeFromStream(body())

            Truth.assertThat(HttpStatusCode.BadRequest).isEqualTo(status)
            Truth.assertThat(error.message).isEqualTo("id must be an integer")
        }

    }

    @Test
    fun `test getRecentLocation route with wrong query data type, returns error`() = testApplication {
        application {
            configureRouting()
        }

        client.get("/recent-location?id=abc").apply {

            val response: ErrorResponse = json.decodeFromStream(body())

            Truth.assertThat(HttpStatusCode.BadRequest).isEqualTo(status)
            Truth.assertThat(response.message).isEqualTo("id must be an integer")
        }
    }


    @Test
    fun `test getRecentLocation route with id not found in resource, returns error`() = testApplication {
        application {
            configureRouting()
        }

        val id = 1000

        client.get("/recent-location?id=$id").apply {

            val response: ErrorResponse = json.decodeFromStream(body())

            Truth.assertThat(HttpStatusCode.NotFound).isEqualTo(status)
            Truth.assertThat(response.message).isEqualTo("No recent location with id $id")
        }

    }

    @Test
    fun `test getRecentLocation with right query parameter, returns success`() = testApplication {
        application {
            configureRouting()
        }

        client.get("/recent-location?id=1").apply {

            val response: Response<RecentLocation> = json.decodeFromStream(body())

            Truth.assertThat(HttpStatusCode.OK).isEqualTo(status)
            Truth.assertThat(response.data).isIn(DummyData.DummyRecentLocations)
        }
    }

}