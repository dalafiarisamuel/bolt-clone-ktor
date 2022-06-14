package com.devtamuno

import com.devtamuno.data.*
import com.devtamuno.plugins.configureRouting
import com.google.common.truth.Truth.assertThat
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import io.ktor.utils.io.jvm.javaio.*
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