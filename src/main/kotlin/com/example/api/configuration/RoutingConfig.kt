package com.example.api.configuration

import com.example.api.router.chatRouter
import com.example.api.router.productRouter
import com.example.api.router.systemRouter
import com.example.api.router.userRouter
import com.example.common.config.logger
import io.github.smiley4.ktorswaggerui.routing.openApiSpec
import io.github.smiley4.ktorswaggerui.routing.swaggerUI
import io.ktor.server.application.Application
import io.ktor.server.routing.*
import io.ktor.util.toMap

fun Route.customRouters() {
    userRouter()
    productRouter()
    chatRouter()
}

fun Application.configureRouting() {
    val log = logger()

    routing {
        route("api.json") {
            openApiSpec()
        }
        route("swagger") {
            swaggerUI("/api.json")
        }
        trace {
            log.debug(it.call.request.headers.toMap().toString())
        }
        systemRouter()
        customRouters()
    }
}
