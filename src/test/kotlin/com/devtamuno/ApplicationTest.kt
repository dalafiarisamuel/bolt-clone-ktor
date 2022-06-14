package com.devtamuno

import com.devtamuno.data.*
import com.devtamuno.plugins.configureRouting
import com.google.common.truth.Truth.assertThat
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import kotlin.test.Test


@OptIn(ExperimentalSerializationApi::class)
class ApplicationTest {


    private val json = Json {
        ignoreUnknownKeys = true
    }

    @Test
    fun testRoot() = testApplication {
        application {
            configureRouting()
        }
        client.get("/").apply {

            assertThat(HttpStatusCode.OK).isEqualTo(status)
            assertThat("Bolt clone root!").isEqualTo(bodyAsText())
        }
    }

    @Test
    fun `test getUser route expecting OK status`() = testApplication {
        application {
            configureRouting()
        }
        client.get("/get-user").apply {

            assertThat(HttpStatusCode.OK).isEqualTo(status)
        }
    }


    @Test
    fun `test getUser route expecting user data to match`() = testApplication {
        application {
            configureRouting()
        }

        client.get("/get-user").apply {

            val user: UserResponse = json.decodeFromStream(body())

            assertThat(HttpStatusCode.OK).isEqualTo(status)
            assertThat(user.user).isEqualTo(DummyData.DummyUser)
        }
    }


    @Test
    fun `test getRecentLocations route expecting OK status`() = testApplication {

        client.get("/recent-locations").apply {
            assertThat(HttpStatusCode.OK).isEqualTo(status)
        }
    }


    @Test
    fun `test getRecentLocations route expecting list of recent locations`() = testApplication {
        application {
            configureRouting()
        }

        client.get("/recent-locations").apply {

            val data: RecentLocationResponse = json.decodeFromStream(body())

            assertThat(HttpStatusCode.OK).isEqualTo(status)
            assertThat(data.list).isNotEmpty()
            assertThat(data.list.size).isEqualTo(DummyData.DummyRecentLocations.size)
        }
    }

    @Test
    fun `test getRecentLocation route with missing query id parameter, returns error `() = testApplication {
        application {
            configureRouting()
        }

        client.get("/recent-locations?id=").apply {

            val error: ErrorResponse = json.decodeFromStream(body())

            assertThat(HttpStatusCode.BadRequest).isEqualTo(status)
            assertThat(error.error).isEqualTo("id must be an integer")
        }

    }

    @Test
    fun `test getRecentLocation route with wrong query data type, returns error`() = testApplication {
        application {
            configureRouting()
        }

        client.get("/recent-locations?id=abc").apply {

            val error: ErrorResponse = json.decodeFromStream(body())

            assertThat(HttpStatusCode.BadRequest).isEqualTo(status)
            assertThat(error.error).isEqualTo("id must be an integer")
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

            assertThat(HttpStatusCode.BadRequest).isEqualTo(status)
            assertThat(error.error).isEqualTo("No recent location with id $id")
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

            assertThat(HttpStatusCode.OK).isEqualTo(status)
            assertThat(data.location).isIn(DummyData.DummyRecentLocations)
        }
    }

    @Test
    fun `test wallet route, expecting OK status`() = testApplication {

        application {
            configureRouting()
        }

        client.get("/user-wallet").apply {
            assertThat(HttpStatusCode.OK).isEqualTo(status)
        }
    }


    @Test
    fun `test wallet route returns data`() = testApplication {
        application {
            configureRouting()
        }

        client.get("/user-wallet").apply {
            val data: WalletResponse = json.decodeFromStream(body())
            assertThat(HttpStatusCode.OK).isEqualTo(status)
            assertThat(data.wallet.balance).isEqualTo(DummyData.DummyWallet.balance)
            assertThat(data.wallet.currency).isEqualTo(DummyData.DummyWallet.currency)
        }
    }


    @Test
    fun `test getTripHistories route, expecting OK status`() = testApplication {

        application {
            configureRouting()
        }

        client.get("/trip-histories").apply {
            assertThat(HttpStatusCode.OK).isEqualTo(status)
        }
    }


    @Test
    fun `test getTripHistories route, returns data list`() = testApplication {

        application {
            configureRouting()
        }

        client.get("/trip-histories").apply {

            val data: TripHistoryResponse = json.decodeFromStream(body())

            assertThat(HttpStatusCode.OK).isEqualTo(status)
            assertThat(data.list).isNotEmpty()
            assertThat(DummyData.DummyTripHistories.size).isEqualTo(data.list.size)
        }
    }
}