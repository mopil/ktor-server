package com.example.domain.model

import org.jetbrains.exposed.dao.id.EntityID

object Users : BaseLongIdTable("users", "user_id") {
    val name = varchar("user_name", 255)
    val age = integer("age")
    val balance = integer("balance").default(0)
}

class User(id: EntityID<Long>) : BaseEntity(id, Users) {
    companion object : BaseEntityClass<User>(Users)
    var name by Users.name
    var age by Users.age
    var balance by Users.balance
}
