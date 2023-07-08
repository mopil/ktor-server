package com.example.api.dto

import com.example.model.domain.User

data class CreateUserRequest(
    val name: String,
    val age: Int
)

data class GetUserResponse(
    val id: Long,
    val name: String,
    val age: Int,
    val balance: Int,
    val createdAt: String,
    val updatedAt: String
) {
    constructor(entity: User) : this(
        id = entity.id.value,
        name = entity.name,
        age = entity.age,
        balance = entity.balance,
        createdAt = entity.createdAt.toString(),
        updatedAt = entity.updatedAt.toString()
    )
}
