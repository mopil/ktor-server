package com.example.common.util

import com.example.api.dto.IdResponse
import com.typesafe.config.ConfigFactory
import io.ktor.server.config.HoconApplicationConfig

fun getConfigProperty(path: String) =
    HoconApplicationConfig(ConfigFactory.load()).property(path).getString()

fun Long.toResponse() = IdResponse(this)
