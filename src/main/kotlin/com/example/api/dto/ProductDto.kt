package com.example.api.dto

import com.example.domain.model.Product
import com.example.domain.model.ProductCategory
import kotlinx.serialization.Serializable

data class CreateProductRequest(
    val name: String,
    val description: String,
    val price: Int
)

enum class ProductPriceCondition {
    LESS_THAN, GREATER_THAN, EXACTLY
}

data class GetProductRequest(
    val name: String? = null,
    val price: Int? = null,
    val priceCondition: ProductPriceCondition? = null,
    val category: ProductCategory? = null,
    val offset: Long,
    val limit: Int
)

@Serializable
data class GetProductResponse(
    val id: Long,
    val name: String,
    val description: String,
    val price: Int
) {
    constructor(entity: Product) : this(
        id = entity.id.value,
        name = entity.name,
        description = entity.description,
        price = entity.price
    )
}
