package com.example.api.dto

data class CreateChatRoomRequest(
    val name: String,
    val maxUser: Int? = 5,
    val password: String? = null
)