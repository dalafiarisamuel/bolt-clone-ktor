package com.devtamuno

import com.devtamuno.data.DummyData
import com.devtamuno.data.RecentLocationResponse
import com.devtamuno.data.UserResponse
import com.devtamuno.data.WalletResponse
import com.devtamuno.plugins.configureRouting
import com.google.common.truth.Truth.assertThat
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import io.ktor.utils.io.jvm.javaio.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import kotlin.test.Test


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
    @OptIn(ExperimentalSerializationApi::class)
    fun `test getUser route expecting user data to match`() = testApplication {
        application {
            configureRouting()
        }

        client.get("/get-user").apply {

            val user: UserResponse = json.decodeFromStream(bodyAsChannel().toInputStream())

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
    @OptIn(ExperimentalSerializationApi::class)
    fun `test getRecentLocations route expecting list of recent locations`() = testApplication {
        application {
            configureRouting()
        }

        client.get("/recent-locations").apply {

            val data: RecentLocationResponse = json.decodeFromStream(bodyAsChannel().toInputStream())

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


    @OptIn(ExperimentalSerializationApi::class)
    @Test
    fun `test wallet route returns data`() = testApplication {
        application {
            configureRouting()
        }

        client.get("/user-wallet").apply {
            val data: WalletResponse = json.decodeFromStream(bodyAsChannel().toInputStream())
            assertThat(HttpStatusCode.OK).isEqualTo(status)
            assertThat(data.wallet).isEqualTo(DummyData.DummyWallet)
        }
    }
}