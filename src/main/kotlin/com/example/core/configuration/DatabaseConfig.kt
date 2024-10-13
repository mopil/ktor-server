package com.example.core.configuration

import com.example.core.configuration.DatabaseUtils.connectDatabase
import com.example.core.configuration.DatabaseUtils.dropAndCreateTables
import com.example.core.util.ApplicationConfigUtils.getDataSource
import com.example.core.util.ApplicationConfigUtils.isDevEnv
import com.example.core.util.ApplicationConfigUtils.isResetTables
import com.example.core.util.ApplicationConfigUtils.isSetDummyData
import com.example.core.util.DummyDataUtils.setDummyData
import com.example.domain.model.ChatRooms
import com.example.domain.model.Chats
import com.example.domain.model.Orders
import com.example.domain.model.Products
import com.example.domain.model.Users
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.Application
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureDatabase() {
    connectDatabase()
    if (isResetTables()) dropAndCreateTables()
    if (isSetDummyData()) setDummyData()
}

object DatabaseUtils {
    private val tables = arrayOf(Users, Orders, Products, Chats, ChatRooms)
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

    fun <T> dbQuery(
        block: () -> T
    ): T = transaction {
        if (isDevEnv()) addLogger(StdOutSqlLogger)
        block()
    }
}
