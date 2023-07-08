package com.example.api

import com.example.api.dto.CreateChatRoomRequest
import com.example.service.ChatService
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import org.koin.ktor.ext.inject

fun Route.chatRouter() {
    val chatService: ChatService by inject()

    post(Uris.Chat.CREATE_CHAT_ROOM) {
        val requestBody = call.receive<CreateChatRoomRequest>()
        call.respond(chatService.createChatRoom(requestBody))
    }
}