package com.devtamuno.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.rootDirectory() {

    get("/") {
        call.respondText(
            text = "Bolt clone root!",
            status = HttpStatusCode.OK
        )
    }
}