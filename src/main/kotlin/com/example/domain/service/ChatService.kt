package com.example.domain.service

import com.example.api.dto.CreateChatRoomRequest
import com.example.api.dto.IdResponse
import com.example.core.util.toResponse
import com.example.domain.model.ChatRepository
import org.jetbrains.exposed.sql.transactions.transaction

class ChatService(
    private val chatRepository: ChatRepository
) {
    fun createChatRoom(request: CreateChatRoomRequest): IdResponse = transaction {
        if (chatRepository.duplicateCheckByName(request.name)) {
            throw IllegalArgumentException("duplicate name")
        }
        val chatRoom = chatRepository.save(request)
        return@transaction chatRoom.id.value.toResponse()
    }
}
