package com.example.core.util

import com.typesafe.config.ConfigFactory
import io.ktor.server.config.HoconApplicationConfig

object ApplicationConfigUtils {
    fun getConfigProperty(path: String) =
        HoconApplicationConfig(ConfigFactory.load()).property(path).getString()

    fun getDataSource(key: String) = getConfigProperty("database.datasource.$key")

    fun isDevEnv() = getConfigProperty("environment") == "dev"
    fun isResetTables() = getConfigProperty("database.reset-on-boot") == "true"
    fun isSetDummyData() = getConfigProperty("database.set-dummy-data") == "true"
}
