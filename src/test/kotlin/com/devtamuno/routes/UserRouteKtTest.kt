package com.devtamuno.routes

import com.devtamuno.data.DummyData
import com.devtamuno.response.Response
import com.devtamuno.data.User
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
class UserRouteKtTest {


    private val json = Json {
        ignoreUnknownKeys = true
    }


    @Test
    fun `test getUser route expecting OK status`() = testApplication {
        application {
            configureRouting()
        }
        client.get("/get-user").apply {

            Truth.assertThat(HttpStatusCode.OK).isEqualTo(status)
        }
    }


    @Test
    fun `test getUser route expecting user data to match`() = testApplication {
        application {
            configureRouting()
        }

        client.get("/get-user").apply {

            val response: Response<User> = json.decodeFromStream(body())

            Truth.assertThat(HttpStatusCode.OK).isEqualTo(status)
            Truth.assertThat(response.data).isEqualTo(DummyData.DummyUser)
        }
    }
}