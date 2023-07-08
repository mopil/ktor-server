package com.example.service

import com.example.api.dto.CreateUserRequest
import com.example.api.dto.GetUserResponse
import com.example.api.dto.IdResponse
import com.example.common.util.toResponse
import com.example.model.domain.UserRepository

class UserService(
    private val userRepository: UserRepository
) {
    suspend fun createUser(request: CreateUserRequest): IdResponse {
        val user = userRepository.save(request)
        return user.id.value.toResponse()
    }

    suspend fun getUser(id: Long): GetUserResponse {
        val user = userRepository.findById(id)
        return GetUserResponse(user)
    }
}
