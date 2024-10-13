package com.example

import com.example.api.configuration.configureRouters
import com.example.api.configuration.configureSwagger
import com.example.core.configuration.configureDatabase
import com.example.core.configuration.configureDependencyInjection
import com.example.core.configuration.configureLogging
import com.example.core.configuration.configureSerialization
import com.example.api.configuration.configureExceptionHandling
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
    configureRouters()
    configureSwagger()
}
