package com.example.model.configuration

import com.example.common.util.getConfigProperty
import com.example.common.util.logger
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.Application
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureDatabase() {
    val log = logger()
    fun datasource(key: String) = getConfigProperty("datasource.$key")
    val hikari = HikariDataSource(HikariConfig().apply {
        jdbcUrl = datasource("url")
        username = datasource("username")
        password = datasource("password")
        driverClassName = datasource("driver-class-name")
        maximumPoolSize = datasource("max-pool-size").toInt()
        isAutoCommit = false
        transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        validate()
    })
    Database.connect(hikari)
    log.info("Database is successfully connected.")
}

object DatabaseConfiguration {
    suspend fun <T> dbQuery(
        block: () -> T
    ): T = withContext(Dispatchers.IO) {
        transaction { block() }
    }
}