package com.example.infrastructure.implementation

import org.jetbrains.exposed.dao.id.EntityID

object Users : BaseLongIdTable("user", "user_id") {
    val nickname = varchar("nickname", 255)
    val email = varchar("email", 255)
    val encPassword = varchar("enc_password", 255)
}

class UserExposedEntity(id: EntityID<Long>) : BaseEntity(id, Users) {
    companion object : BaseEntityClass<UserExposedEntity>(Users)
    var nickname by Users.nickname
    var email by Users.email
    var encPassword by Users.encPassword
}
