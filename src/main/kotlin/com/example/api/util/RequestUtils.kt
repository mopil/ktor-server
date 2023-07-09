package com.example.api.util

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.ktor.http.Parameters
import io.ktor.server.application.ApplicationCall


object RequestUtils {
    val mapper = jacksonObjectMapper()

    fun ApplicationCall.getEntityId(): Long =
        parameters["id"]?.toLong() ?: throw IllegalArgumentException("The 'id' parameter is missing")

    inline fun <reified T : Any> Parameters.toClass(): T {
        val map = entries().associate { it.key to (it.value.getOrNull(0) ?: throw IllegalArgumentException("Missing value for key ${it.key}")) }
        return mapper.convertValue(map, T::class.java)
    }

    inline fun <reified T : Any> ApplicationCall.getQueryParams(): T {
        return this.request.queryParameters.toClass()
    }
}

