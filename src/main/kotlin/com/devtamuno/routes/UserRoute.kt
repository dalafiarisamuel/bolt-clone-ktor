package com.devtamuno.routes

import com.devtamuno.data.DummyData
import com.devtamuno.response.Response
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getUser() {
    get("/get-user") {
        call.respond(
            HttpStatusCode.OK,
            Response(
                success = true,
                statusCode = HttpStatusCode.OK.value,
                message = "data returned successfully",
                data = DummyData.DummyUser
            )
        )
    }
}