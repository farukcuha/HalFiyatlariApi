package com.pandorina

import com.pandorina.data.local.PriceTable
import com.typesafe.config.ConfigFactory
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.config.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import javax.sql.DataSource

object AppDatabase {
    fun init() {
        /*Database.connect(
            url = System.getenv("DATABASE_URL"),
            driver = System.getenv("DRIVER"),
            user = System.getenv("USER_NAME"),
            password = System.getenv("PASSWORD")
        )*/
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