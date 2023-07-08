package com.example.common.config

import com.example.common.config.DatabaseUtils.connectDatabase
import com.example.common.config.DatabaseUtils.dropAndCreateTables
import com.example.common.util.ApplicationConfigUtils.getDataSource
import com.example.common.util.ApplicationConfigUtils.isDevEnv
import com.example.common.util.ApplicationConfigUtils.isResetTables
import com.example.common.util.ApplicationConfigUtils.isSetDummyData
import com.example.common.util.DummyDataUtils.setDummyData
import com.example.model.domain.ChatRooms
import com.example.model.domain.Chats
import com.example.model.domain.Orders
import com.example.model.domain.Products
import com.example.model.domain.Users
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
