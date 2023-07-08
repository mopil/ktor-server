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
}
