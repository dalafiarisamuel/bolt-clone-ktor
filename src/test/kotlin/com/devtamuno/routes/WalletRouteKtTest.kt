package com.devtamuno.routes

import com.devtamuno.data.DummyData
import com.devtamuno.data.LinkedCardResponse
import com.devtamuno.data.WalletResponse
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
            val data: WalletResponse = json.decodeFromStream(body())
            Truth.assertThat(HttpStatusCode.OK).isEqualTo(status)
            Truth.assertThat(data.wallet.balance).isEqualTo(DummyData.DummyWallet.balance)
            Truth.assertThat(data.wallet.currency).isEqualTo(DummyData.DummyWallet.currency)
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

            val cardList: LinkedCardResponse = json.decodeFromStream(body())

            Truth.assertThat(HttpStatusCode.OK).isEqualTo(status)
            Truth.assertThat(cardList.list).isNotEmpty()
            Truth.assertThat(cardList.list.size).isEqualTo(DummyData.DummyAtmCards.size)
        }
    }
}