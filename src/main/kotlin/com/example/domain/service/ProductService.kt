package com.example.domain.service

import com.example.api.dto.CreateProductRequest
import com.example.api.dto.GetProductRequest
import com.example.api.dto.GetProductResponse
import com.example.api.dto.IdResponse
import com.example.api.util.PaginationUtils.PageResponse
import com.example.core.util.toResponse
import com.example.domain.model.ProductRepository

class ProductService(
    private val productRepository: ProductRepository
) {
    suspend fun createProduct(request: CreateProductRequest): IdResponse {
        val product = productRepository.save(request)
        return product.id.value.toResponse()
    }
    suspend fun getAllProducts(request: GetProductRequest): PageResponse<GetProductResponse> {
        return productRepository.findAllByCondition(request)
    }
}
