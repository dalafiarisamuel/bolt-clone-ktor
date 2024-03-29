package com.devtamuno.routes

import com.devtamuno.data.DummyData
import com.devtamuno.response.Response
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.getWalletBalance() {

    get("/user-wallet") {
        call.respond(
            HttpStatusCode.OK,
            Response(
                success = true,
                statusCode = HttpStatusCode.OK.value,
                message = "data returned successfully",
                data = DummyData.DummyWallet
            )
        )
    }

}


fun Route.getLinkedDebitCards() {

    get("/user-linked-cards") {
        call.respond(
            HttpStatusCode.OK,
            Response(
                success = true,
                statusCode = HttpStatusCode.OK.value,
                message = "data returned successfully",
                data = DummyData.DummyAtmCards
            )
        )
    }
}