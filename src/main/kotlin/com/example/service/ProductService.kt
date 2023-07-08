package com.example.service

import com.example.api.dto.CreateProductRequest
import com.example.api.dto.GetProductRequest
import com.example.api.dto.GetProductResponse
import com.example.api.dto.IdResponse
import com.example.common.util.PaginationUtils.PageResponse
import com.example.common.util.toResponse
import com.example.model.domain.ProductRepository

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
