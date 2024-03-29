package com.devtamuno.routes


import com.devtamuno.data.DummyData
import com.devtamuno.response.ErrorResponse
import com.devtamuno.response.Response
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.getRecentLocations() {

    get("/recent-locations") {
        call.respond(
            HttpStatusCode.OK,
            Response(
                success = true,
                statusCode = HttpStatusCode.OK.value,
                message = "data returned successfully",
                data = DummyData.DummyRecentLocations
            )
        )
    }
}


fun Route.getRecentLocation() {

    get("/recent-location") {

        if (call.request.queryParameters.isEmpty()) {

            return@get call.respond(
                message = ErrorResponse(
                    success = false,
                    statusCode = HttpStatusCode.BadRequest.value,
                    message = "id parameter is missing",
                ),
                status = HttpStatusCode.BadRequest
            )
        }

        if (call.request.queryParameters["id"] != null) {

            val id: String = call.request.queryParameters["id"]!!

            id.toLongOrNull() ?: return@get call.respond(
                message = ErrorResponse(
                    success = false,
                    statusCode = HttpStatusCode.BadRequest.value,
                    message = "id must be an integer",
                ),
                status = HttpStatusCode.BadRequest
            )

            val recentLocation = DummyData.DummyRecentLocations.find { it.id == id.toLong() }
                ?: return@get call.respond(
                    message = ErrorResponse(
                        success = false,
                        statusCode = HttpStatusCode.NotFound.value,
                        message = "No recent location with id $id",
                    ),
                    status = HttpStatusCode.NotFound
                )

            call.respond(
                status = HttpStatusCode.OK,
                message = Response(
                    success = true,
                    statusCode = HttpStatusCode.OK.value,
                    message = "data returned successfully",
                    data = recentLocation
                )
            )

        }
    }
}