package com.example.api.router

import com.example.api.dto.CreateUserRequest
import com.example.api.dto.GetUserResponse
import com.example.api.dto.IdResponse
import com.example.api.util.RequestUtils.getEntityId
import com.example.api.util.SwaggerUtils.internalServerError
import com.example.api.util.SwaggerUtils.notFound
import com.example.domain.service.UserService
import io.github.smiley4.ktorswaggerui.dsl.routing.get
import io.github.smiley4.ktorswaggerui.dsl.routing.post
import io.ktor.http.*
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import org.koin.ktor.ext.inject

fun Route.userRouter() {
    val userService: UserService by inject()

    post("/users", {
        tags = listOf("user")
        description = "create user"
        request {
            body<CreateUserRequest>()
        }
        response {
            HttpStatusCode.OK to {
                body<IdResponse>()
            }
            HttpStatusCode.InternalServerError to internalServerError()
        }
    }) {
        val requestBody = call.receive<CreateUserRequest>()
        val response = userService.createUser(requestBody)
        call.respond(response)
    }

    get("/users/{id}",  {
        tags = listOf("user")
        description = "get user"
        request {
            pathParameter<Long>("id")
        }
        response {
            HttpStatusCode.OK to {
                body<GetUserResponse>()
            }
            HttpStatusCode.NotFound to notFound()
            HttpStatusCode.InternalServerError to internalServerError()
        }
    }) {
        val userId = call.getEntityId()
        call.respond(userService.getUser(userId))
    }
}
