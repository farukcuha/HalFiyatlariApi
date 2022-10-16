package com.pandorina

import com.pandorina.data.local.city.CityTable
import com.pandorina.data.local.photo.PhotosDataSource
import com.pandorina.data.local.photo.PhotosTable
import com.pandorina.data.local.price.PriceTable
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

object AppDatabase {
    fun init() {
        Database.connect(hikari())
        transaction {
            SchemaUtils.create(PriceTable)
            SchemaUtils.create(PhotosTable)
            SchemaUtils.create(CityTable)
        }
    }
    private fun hikari(): HikariDataSource {
        val config = HikariConfig()
        config.driverClassName = System.getenv("DATABASE_DRIVER")
        config.jdbcUrl = System.getenv("DATABASE_URL")
        config.username = System.getenv("DATABASE_USERNAME")
        config.password = System.getenv("DATABASE_PASSWORD")
        config.maximumPoolSize = 3
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        config.validate()
        return HikariDataSource(config)
    }
}