package com.example.api.config

import com.example.api.chatRouter
import com.example.api.productRouter
import com.example.api.systemRouter
import com.example.api.userRouter
import com.example.common.config.logger
import io.ktor.server.application.Application
import io.ktor.server.plugins.swagger.swaggerUI
import io.ktor.server.routing.routing
import io.ktor.util.toMap

fun Application.configureRouting() {
    val log = logger()

    routing {
        trace {
            log.debug(it.call.request.headers.toMap().toString())
        }
        swaggerUI(path = "openapi")
        systemRouter()
        userRouter()
        productRouter()
        chatRouter()
    }
}
