package com.devtamuno.plugins

import com.devtamuno.routes.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*

fun Application.configureRouting() {

    routing {

        rootDirectory()
        getUser()
        getTripHistories()
        getRecentLocations()
        getRecentLocation()
        getWalletBalance()
        getLinkedDebitCards()

        // Static plugin. Try to access `/static/index.html`
        static("/static") {
            resources("static")
        }
    }
}
