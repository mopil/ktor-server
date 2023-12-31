package com.example.api

import com.example.api.config.Uris
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get

fun Route.systemRouter() {
    get(Uris.HEALTH) {
        call.respond("OK")
    }
}
