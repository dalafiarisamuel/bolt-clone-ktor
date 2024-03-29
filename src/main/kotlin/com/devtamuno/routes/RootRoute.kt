package com.devtamuno.routes

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.get


fun Route.rootDirectory() {

    get("/") {
        call.respondText(
            text = "Bolt clone root!",
            status = HttpStatusCode.OK
        )
    }
}