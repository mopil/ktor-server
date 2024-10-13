package com.example.domain.model

import org.jetbrains.exposed.dao.id.EntityID

object ChatRooms : BaseLongIdTable("chat_room", "chat_room_id") {
    val name = varchar("name", 255)
    var password = varchar("password", 255).nullable()
    val maxUser = integer("max_user").default(0)
    val currentUser = integer("current_user").default(0)
    val totalChats = integer("total_chats").default(0)
    val owner = reference("user_id", Users).nullable()
}

class ChatRoom(id: EntityID<Long>) : BaseEntity(id, ChatRooms) {
    companion object : BaseEntityClass<ChatRoom>(ChatRooms)
    var name by ChatRooms.name
    var password by ChatRooms.password
    var maxUser by ChatRooms.maxUser
    var currentUser by ChatRooms.currentUser
    var totalChats by ChatRooms.totalChats
    var owner by ChatRooms.owner
}

object Chats : BaseLongIdTable("chat", "chat_id") {
    val content = varchar("content", 5000)
    val sender = reference("user_id", Users)
}

class Chat(id: EntityID<Long>) : BaseEntity(id, Chats) {
    companion object : BaseEntityClass<Chat>(Chats)
    var content by Chats.content
    var sender by Chats.sender
}
