package com.devtamuno.routes

import com.devtamuno.data.DummyData
import com.devtamuno.data.UserResponse
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
class UserRouteTest {


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

            val user: UserResponse = json.decodeFromStream(body())

            Truth.assertThat(HttpStatusCode.OK).isEqualTo(status)
            Truth.assertThat(user.user).isEqualTo(DummyData.DummyUser)
        }
    }
}