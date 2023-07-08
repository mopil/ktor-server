package com.example.model.domain

import com.example.api.dto.CreateUserRequest
import com.example.common.config.DatabaseUtils.dbQuery

interface UserRepository {
    suspend fun save(request: CreateUserRequest): User
    suspend fun findById(id: Long): User
}

class UserRepositoryImpl : UserRepository {
    override suspend fun save(request: CreateUserRequest) = dbQuery {
        User.new {
            this.name = request.name
            this.age = request.age
        }
    }

    override suspend fun findById(id: Long) = dbQuery {
        User.findById(id) ?: throw NoSuchElementException()
    }
}
