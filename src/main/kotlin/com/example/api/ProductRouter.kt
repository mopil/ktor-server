package com.example.api

import com.example.api.dto.GetProductRequest
import com.example.service.ProductService
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import org.koin.ktor.ext.inject

fun Route.productRouter() {
    val productService: ProductService by inject()

    get(Uris.Product.GET_ALL_PRODUCTS) {
        val params = GetProductRequest(call.parameters)
        call.respond(productService.getAllProducts(params))
    }
}
