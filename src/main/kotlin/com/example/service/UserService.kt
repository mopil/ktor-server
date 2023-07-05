package com.example.service

import com.example.model.domain.User
import com.example.model.domain.UserRepository
import org.jetbrains.exposed.sql.transactions.transaction

class UserService(
    private val userRepository: UserRepository
) {
    fun createUser(name: String, age: Int) = transaction {
        User.new {
            this.name = name
            this.age = age
        }
    }
}