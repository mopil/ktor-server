package com.example

import com.example.api.configureRouting
import com.example.common.config.configureDatabase
import com.example.common.config.configureDependencyInjection
import com.example.common.config.configureLogging
import com.example.common.config.configureSerialization
import com.example.common.configureExceptionHandling
import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main() {
    embeddedServer(Netty, port = 8080, module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureDatabase()
    configureLogging()
    configureExceptionHandling()
    configureSerialization()
    configureDependencyInjection()
    configureRouting()
}
