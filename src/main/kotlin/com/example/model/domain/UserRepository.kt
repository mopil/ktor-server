package com.example.model.domain

import com.example.model.configuration.DatabaseConfiguration.dbQuery
import com.example.model.dto.UserDto

class UserRepository {
    suspend fun save(user: UserDto): Int = dbQuery {
        User.new {
            name = user.name
            age = user.age
        }.id.value
    }
}