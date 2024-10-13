package com.example.infrastructure.implementation

import com.example.core.configuration.logger
import java.time.LocalDateTime
import org.jetbrains.exposed.dao.EntityChangeType
import org.jetbrains.exposed.dao.EntityHook
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.dao.toEntity
import org.jetbrains.exposed.sql.javatime.datetime

abstract class BaseLongIdTable(name: String, idName: String = "id") : LongIdTable(name, idName) {
    val createdAt = datetime("created_at").clientDefault { LocalDateTime.now() }
    val updatedAt = datetime("updated_at").clientDefault { LocalDateTime.now() }
}

abstract class BaseEntity(id: EntityID<Long>, table: BaseLongIdTable) : LongEntity(id) {
    val createdAt by table.createdAt
    var updatedAt by table.updatedAt
}

abstract class BaseEntityClass<E : BaseEntity>(table: BaseLongIdTable) : LongEntityClass<E>(table) {
    private val log = logger()
    init {
        EntityHook.subscribe { action ->
            if (action.changeType == EntityChangeType.Updated || action.changeType == EntityChangeType.Created) {
                try {
                    action.toEntity(this)?.updatedAt = LocalDateTime.now()
                } catch (e: Exception) {
                    log.warn("Failed to update entity $this updatedAt ${e.message}")
                }
            }
        }
    }
}
