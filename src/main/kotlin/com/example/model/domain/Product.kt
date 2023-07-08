package com.example.model.domain

import com.example.model.BaseEntity
import com.example.model.BaseEntityClass
import com.example.model.BaseLongIdTable
import org.jetbrains.exposed.dao.id.EntityID

object Products : BaseLongIdTable("product", "product_id") {
    val name = varchar("product_name", 255)
    val description = varchar("description", 255)
    val price = integer("price")
}

class Product(id: EntityID<Long>) : BaseEntity(id, Products) {
    companion object : BaseEntityClass<Product>(Products)
    var name by Products.name
    var description by Products.description
    var price by Products.price
}
