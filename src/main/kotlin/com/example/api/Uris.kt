package com.example.api

object Uris {
    const val HEALTH = "/health"

    object User {
        private const val USER = "/users"
        const val CREATE_USER = USER
        const val GET_USER = "$USER/{id}"
        const val UPDATE_USER = "$USER/{id}"
        const val DELETE_USER = "$USER/{id}"
    }

    object Product {
        private const val PRODUCT = "/products"
        const val CREATE_PRODUCT = PRODUCT
        const val GET_PRODUCT = "$PRODUCT/{id}"
        const val GET_ALL_PRODUCTS = PRODUCT
        const val UPDATE_PRODUCT = "$PRODUCT/{id}"
        const val DELETE_DELETE = "$PRODUCT/{id}"
    }

    object Chat {
        private const val CHAT_ROOM = "/chat-rooms"
        const val CREATE_CHAT_ROOM = CHAT_ROOM
    }
}
