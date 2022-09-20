package com.pandorina

import com.pandorina.data.local.PriceTable
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object AppDatabase {
    fun init() {
        Database.connect(hikari())
        transaction {
            SchemaUtils.create(PriceTable)
        }
    }

    private fun hikari(): HikariDataSource {
        val config = HikariConfig()
        config.driverClassName = System.getenv("DRIVER")
        config.jdbcUrl = System.getenv("DATABASE_URL")
        config.username = System.getenv("USER_NAME")
        config.password = System.getenv("PASSWORD")
        config.maximumPoolSize = 3
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        config.validate()
        return HikariDataSource(config)
    }
}