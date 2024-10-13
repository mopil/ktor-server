package com.example.api.configuration

import com.example.core.util.ApplicationConfigUtils
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.jwt.jwt

fun Application.configureAuthentication() {
    install(Authentication) {
        jwt {
            realm = ApplicationConfigUtils.getConfigProperty("jwt.secret")
        }
    }
}
