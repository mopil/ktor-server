package com.example.api.util

import io.ktor.server.application.ApplicationCall

object RequestUtils {
    fun ApplicationCall.getEntityId() =
        this.parameters["id"]?.toLong() ?: throw IllegalArgumentException()
}
