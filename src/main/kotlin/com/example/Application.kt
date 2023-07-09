package com.example

import com.example.api.config.configureRouting
import com.example.common.config.configureDatabase
import com.example.common.config.configureDependencyInjection
import com.example.common.config.configureLogging
import com.example.common.config.configureSerialization
import com.example.common.configureExceptionHandling
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.routing.routing
import io.ktor.server.websocket.WebSockets
import io.ktor.server.websocket.pingPeriod
import io.ktor.server.websocket.timeout
import io.ktor.server.websocket.webSocket
import io.ktor.websocket.CloseReason
import io.ktor.websocket.Frame
import io.ktor.websocket.close
import io.ktor.websocket.readText
import java.time.Duration

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
