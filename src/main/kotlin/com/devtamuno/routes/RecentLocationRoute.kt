package com.devtamuno.routes


import com.devtamuno.data.DummyData
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.getRecentLocations() {

    get("/recent-locations") {

        if (call.request.queryParameters.isEmpty()) {

            call.respond(
                HttpStatusCode.OK,
                mapOf("list" to DummyData.DummyRecentLocations)
            )
        }

        if (call.request.queryParameters["id"] != null) {

            val id: String = call.request.queryParameters["id"]!!

            id.toLongOrNull() ?: return@get call.respond(
                message = mapOf("error" to "id must be an integer"),
                status = HttpStatusCode.BadRequest
            )

            val recentLocation = DummyData.DummyRecentLocations.find { it.id == id.toLong() }
                ?: return@get call.respond(
                    message = mapOf("error" to "No recent location with id $id"),
                    status = HttpStatusCode.BadRequest
                )

            call.respond(
                status = HttpStatusCode.OK,
                message = mapOf("location" to recentLocation)
            )

        }


    }
}