package com.example.model.domain

import com.example.api.dto.CreateChatRoomRequest
import com.example.common.config.DatabaseUtils.dbQuery

class ChatRepository {

    fun duplicateCheckByName(name: String): Boolean = dbQuery {
        ChatRoom.find { ChatRooms.name eq name }.firstOrNull() != null
    }

    fun save(request: CreateChatRoomRequest): ChatRoom = dbQuery {
        ChatRoom.new {
            name = request.name
            maxUser = request.maxUser ?: 5
            password = request.password
        }
    }
}