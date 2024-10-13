package com.example.domain.model

import com.example.infrastructure.implementation.UserExposedEntity
import java.time.LocalDateTime

class User private constructor(
    val id: Long,
    val email: String,
    val encPassword: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    nickname: String
) {
    var nickname: String = nickname
        private set

    fun updateUserInfo(
        nickname: String
    ) {
        this.nickname = nickname
    }

    companion object {
        fun create(
            nickname: String,
            email: String,
            encPassword: String
        ) = User(
            id = 0L,
            nickname = nickname,
            email = email,
            encPassword = encPassword,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )

        fun from(userExposedEntity: UserExposedEntity) = User(
            id = userExposedEntity.id.value,
            nickname = userExposedEntity.nickname,
            email = userExposedEntity.email,
            encPassword = userExposedEntity.encPassword,
            createdAt = userExposedEntity.createdAt,
            updatedAt = userExposedEntity.updatedAt
        )
    }
}
