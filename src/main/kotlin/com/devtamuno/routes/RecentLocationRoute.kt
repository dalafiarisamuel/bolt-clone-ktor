package com.devtamuno.routes


import com.devtamuno.data.DummyData
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.getRecentLocations() {
    get("/recent-locations") {
        call.respond(
            HttpStatusCode.OK,
            mapOf("list" to DummyData.DummyRecentLocations)
        )
    }
}