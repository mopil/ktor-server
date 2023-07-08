package com.example.api

import com.example.api.dto.CreateUserRequest
import com.example.api.util.RequestUtils.getEntityId
import com.example.service.UserService
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import org.koin.ktor.ext.inject

fun Route.userRouter() {
    val userService: UserService by inject()

    post(Uris.User.CREATE_USER) {
        val requestBody = call.receive<CreateUserRequest>()
        val response = userService.createUser(requestBody)
        call.respond(response)
    }

    get(Uris.User.GET_USER) {
        call.respond(userService.getUser(call.getEntityId()))
    }
}
