package com.example.service

import com.example.api.dto.CreateChatRoomRequest
import com.example.api.dto.IdResponse
import com.example.common.util.toResponse
import com.example.model.domain.ChatRepository
import org.jetbrains.exposed.sql.transactions.transaction

class ChatService(
    private val chatRepository: ChatRepository
) {
    fun createChatRoom(request: CreateChatRoomRequest): IdResponse = transaction {
        if (chatRepository.duplicateCheckByName(request.name))
            throw IllegalArgumentException("duplicate name")
        val chatRoom = chatRepository.save(request)
        return@transaction chatRoom.id.value.toResponse()
    }
}