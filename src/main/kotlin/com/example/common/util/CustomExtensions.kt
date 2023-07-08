package com.example.common.util

import com.typesafe.config.ConfigFactory
import io.ktor.server.config.HoconApplicationConfig
import org.slf4j.Logger
import org.slf4j.LoggerFactory

fun getConfigProperty(path: String) =
    HoconApplicationConfig(ConfigFactory.load()).property(path).getString()

inline fun <reified T> T.logger(): Logger = LoggerFactory.getLogger(T::class.java)
