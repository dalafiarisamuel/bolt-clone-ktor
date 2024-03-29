package com.devtamuno.routes

import com.devtamuno.data.DebitCard
import com.devtamuno.data.DummyData
import com.devtamuno.response.Response
import com.devtamuno.data.Wallet
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
internal class WalletRouteKtTest {

    private val json = Json {
        ignoreUnknownKeys = true
    }

    @Test
    fun `test wallet route, expecting OK status`() = testApplication {

        application {
            configureRouting()
        }

        client.get("/user-wallet").apply {
            Truth.assertThat(HttpStatusCode.OK).isEqualTo(status)
        }
    }


    @Test
    fun `test wallet route returns data`() = testApplication {
        application {
            configureRouting()
        }

        client.get("/user-wallet").apply {
            val response: Response<Wallet> = json.decodeFromStream(body())
            Truth.assertThat(HttpStatusCode.OK).isEqualTo(status)
            Truth.assertThat(response.data).isEqualTo(DummyData.DummyWallet.balance)
            Truth.assertThat(response.data?.currency).isEqualTo(DummyData.DummyWallet.currency)
        }
    }


    @Test
    fun `test get linked cards route, expecting OK status`() = testApplication {

        application {
            configureRouting()
        }

        client.get("/user-linked-cards").apply {
            Truth.assertThat(HttpStatusCode.OK).isEqualTo(status)
        }
    }

    @Test
    fun `test get linked cards route, expecting a list of cards`() = testApplication {
        application {
            configureRouting()
        }

        client.get("/user-linked-cards").apply {
            val response: Response<List<DebitCard>> = json.decodeFromStream(body())

            Truth.assertThat(HttpStatusCode.OK).isEqualTo(status)
            Truth.assertThat(response.data).isNotEmpty()
            Truth.assertThat(response.data?.size).isEqualTo(DummyData.DummyAtmCards.size)
        }
    }
}