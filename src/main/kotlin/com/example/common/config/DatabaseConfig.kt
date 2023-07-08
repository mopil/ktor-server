package com.example.common.config

import com.example.common.config.DatabaseUtils.connectDatabase
import com.example.common.config.DatabaseUtils.dropAndCreateTables
import com.example.common.util.ApplicationConfigUtils.getDataSource
import com.example.common.util.ApplicationConfigUtils.isDevEnv
import com.example.common.util.ApplicationConfigUtils.isResetTables
import com.example.model.domain.Orders
import com.example.model.domain.Products
import com.example.model.domain.Users
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.Application
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureDatabase() {
    connectDatabase()
    if (isResetTables()) dropAndCreateTables()
}

object DatabaseUtils {
    private val tables = arrayOf(Users, Orders, Products)
    private val log = logger()
    fun connectDatabase() {
        val hikari = HikariDataSource(
            HikariConfig().apply {
                jdbcUrl = getDataSource("url")
                username = getDataSource("username")
                password = getDataSource("password")
                driverClassName = getDataSource("driver-class-name")
                maximumPoolSize = getDataSource("max-pool-size").toInt()
                isAutoCommit = false
                transactionIsolation = "TRANSACTION_REPEATABLE_READ"
                validate()
            }
        )
        Database.connect(hikari)
        log.info("Database is successfully connected.")
    }

    fun dropAndCreateTables() = transaction {
        SchemaUtils.drop(*tables)
        SchemaUtils.create(*tables)
        log.info("${tables.size} tables are successfully dropped and created.")
    }

    suspend fun <T> dbQuery(
        block: () -> T
    ): T = withContext(Dispatchers.IO) {
        transaction {
            if (isDevEnv()) addLogger(StdOutSqlLogger)
            block()
        }
    }
}
