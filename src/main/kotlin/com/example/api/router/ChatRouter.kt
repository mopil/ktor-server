package com.example.api.router

import com.example.api.dto.CreateChatRoomRequest
import com.example.api.dto.IdResponse
import com.example.api.util.SwaggerUtils.internalServerError
import com.example.domain.service.ChatService
import io.github.smiley4.ktorswaggerui.dsl.routing.post
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import org.koin.ktor.ext.inject

val chatTags = listOf("Chat")

fun Route.chatRouter() {
    val chatService: ChatService by inject()

    post("/chats", {
        tags = chatTags
        description = "create chat"
        response {
            HttpStatusCode.OK to {
                body<IdResponse>()
            }
            HttpStatusCode.InternalServerError to internalServerError()
        }
    }) {
        val requestBody = call.receive<CreateChatRoomRequest>()
        call.respond(chatService.createChatRoom(requestBody))
    }
}
