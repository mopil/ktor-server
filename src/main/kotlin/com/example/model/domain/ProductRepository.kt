package com.example.model.domain

import com.example.api.dto.CreateProductRequest
import com.example.api.dto.GetProductRequest
import com.example.api.dto.GetProductResponse
import com.example.api.dto.ProductPriceCondition
import com.example.common.config.DatabaseUtils.dbQuery
import com.example.common.util.PaginationUtils.PageResponse
import com.example.common.util.PaginationUtils.toPageResponse
import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.selectAll

interface ProductRepository {
    suspend fun save(request: CreateProductRequest): Product
    suspend fun findById(id: Long): Product
    suspend fun findAllByCondition(request: GetProductRequest): PageResponse<GetProductResponse>
}

class ProductRepositoryImpl : ProductRepository {
    override suspend fun save(request: CreateProductRequest) = dbQuery {
        Product.new {
            this.name = request.name
            this.description = request.description
            this.price = request.price
        }
    }

    override suspend fun findById(id: Long) = dbQuery {
        Product.findById(id) ?: throw NoSuchElementException()
    }

    override suspend fun findAllByCondition(request: GetProductRequest): PageResponse<GetProductResponse> = dbQuery {
        val query = Products.selectAll()
        request.name?.let { query.andWhere { Products.name like it } }
        request.price?.let {
            when (request.priceCondition) {
                ProductPriceCondition.LESS_THAN -> query.andWhere { Products.price less it }
                ProductPriceCondition.GREATER_THAN -> query.andWhere { Products.price greater it }
                else -> query.andWhere { Products.price eq it }
            }
        }
        request.category?.let { query.andWhere { Products.category eq it } }
        val totalCount = query.count()
        query.limit(request.limit, request.offset)
        val list = query.map { Product.wrapRow(it) }.map { GetProductResponse(it) }
        return@dbQuery list.toPageResponse(request.offset, request.limit, totalCount)
    }
}
