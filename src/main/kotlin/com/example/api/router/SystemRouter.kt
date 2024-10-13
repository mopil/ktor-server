package com.example.api.router

import io.github.smiley4.ktorswaggerui.dsl.routing.get
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get

fun Route.systemRouter() {
    get("/health", {
        hidden = true
    }) {
        call.respond("OK")
    }
}
