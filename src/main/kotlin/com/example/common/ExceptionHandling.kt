package com.example.common

import com.example.common.config.logger
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respond

data class ErrorResponse(
    val errorCode: String = "UNKNOWN_ERROR",
    val message: String? = null,
)

fun Application.configureExceptionHandling() {
    val log = logger()
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            log.warn("[Global Ex] $cause")
            call.respond(HttpStatusCode.BadRequest, ErrorResponse(message = cause.localizedMessage))
        }

        exception<NoSuchElementException> { call, cause ->
            call.respond(HttpStatusCode.NotFound, cause.localizedMessage)
        }
    }
}
