package com.devtamuno

import com.devtamuno.plugins.configureRouting
import com.google.common.truth.Truth.assertThat
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test


class ApplicationKtTest {

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


}