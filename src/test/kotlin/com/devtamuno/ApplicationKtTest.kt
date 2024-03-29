package com.devtamuno

import com.devtamuno.plugins.configureRouting
import com.google.common.truth.Truth.assertThat
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.testApplication
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