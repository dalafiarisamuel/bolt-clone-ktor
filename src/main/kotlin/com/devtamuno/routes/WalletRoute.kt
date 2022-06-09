package com.devtamuno.routes

import com.devtamuno.data.DummyData
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.getWalletBalance() {

    get("/user-wallet") {
        call.respond(
            HttpStatusCode.OK,
            mapOf("wallet" to DummyData.DummyWallet)
        )
    }

}


fun Route.getLinkedDebitCards() {

    get("/user-linked-cards") {
        call.respond(
            HttpStatusCode.OK,
            mapOf("list" to DummyData.DummyAtmCards)
        )
    }
}