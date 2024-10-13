package com.example.infrastructure.implementation

import com.example.domain.model.User
import com.example.domain.model.UserRepository

/**
 * Exposed ORM 구현체
 *
 * ORM 라이브러리를 변경하더라도 서비스레이어에 수정이 없도록 구현하였다.
 * 이러니 도메인 엔티티 클래스를 따로 만들어야하고, 더티체킹도 적용하기 힘든데 더 연구해볼 것
 */
class UserRepositoryExposedImpl : UserRepository {
    override fun findByEmailOrNull(email: String): User? {
        return UserExposedEntity.find {
            Users.email eq email
        }.singleOrNull()
            ?.let { User.from(it) }
    }

    override fun findByNicknameOrNull(nickname: String): User? {
        return UserExposedEntity.find {
            Users.nickname eq nickname
        }.singleOrNull()
            ?.let { User.from(it) }
    }

    override fun save(user: User): User {
        val existUser = UserExposedEntity.findById(user.id)
        return (
            existUser?.apply {
                nickname = user.nickname
            }
                ?: UserExposedEntity.new {
                    this.nickname = user.nickname
                    this.email = user.email
                    this.encPassword = user.encPassword
                }
            ).let { User.from(it) }
    }

    override fun findById(id: Long) =
        UserExposedEntity.findById(id)?.let {
            User.from(it)
        } ?: throw NoSuchElementException()
}
