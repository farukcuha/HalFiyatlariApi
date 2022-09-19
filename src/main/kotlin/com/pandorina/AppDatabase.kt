package com.pandorina

import com.typesafe.config.ConfigFactory
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.config.*
import org.jetbrains.exposed.sql.Database
import javax.sql.DataSource

object AppDatabase {
    lateinit var dataSource: DataSource

    fun init() {
        connectionPool()
        orm()
    }

    private fun connectionPool() {
        val dbConfig = HoconApplicationConfig(ConfigFactory.load())
        val config = HikariConfig().apply {
            jdbcUrl = dbConfig.property("jdbc.url").getString()
            driverClassName = dbConfig.property("jdbc.driver").getString()
            username = dbConfig.property("jdbc.username").getString()
            password = dbConfig.property("jdbc.password").getString()
            isAutoCommit = false
            maximumPoolSize = 3
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            validate()
        }
        dataSource = HikariDataSource(config)
    }

    private fun orm() = Database.connect(dataSource)
}