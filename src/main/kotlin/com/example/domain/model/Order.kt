package com.example.domain.model

import org.jetbrains.exposed.dao.id.EntityID

enum class OrderStatus(val description: String) {
    PENDING("주문진행중"),
    PAYMENT_COMPLETED("결제완료"),
    PAYMENT_FAILED("결제실패"),
    COMPLETED("주문완료"),
    DELIVERING("배송중"),
    DELIVERED("배송완료"),
    CANCELED("취소됨"),
    REFUNDING("환불중"),
    REFUNDED("환불됨")
}

object Orders : BaseLongIdTable("order", "order_id") {
    val status = enumerationByName<OrderStatus>("status", 255)
    val product = reference("product_id", Products)
    val user = reference("user_id", Users)
}

class Order(id: EntityID<Long>) : BaseEntity(id, Orders) {
    companion object : BaseEntityClass<Order>(Orders)
    var status by Orders.status
    var product by Product referencedOn Orders.product
    var user by User referencedOn Orders.user
}
