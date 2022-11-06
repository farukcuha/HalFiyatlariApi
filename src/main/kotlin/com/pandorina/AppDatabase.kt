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
        /*transaction {
            PriceTable.insert {
                it[id] = "5 Kasım 2022 Cumartesi - Patlıcan - 04.00₺"
                it[cityId] = "bozyazi"
                it[priceDate] = "5 Kasım 2022 Cumartesi"
                it[lastUpdatedTime] = System.currentTimeMillis()
                it[name] = "Patlıcan"
                it[icon] = "https://cdn-icons-png.flaticon.com/512/766/766026.png"
                it[measure] = "Kilogram"
                it[pricePrimary] = "04.00₺"
                it[priceSecondary] = null
            }
            PriceTable.insert {
                it[id] = "5 Kasım 2022 Cumartesi - Salatalık - 06.00₺"
                it[cityId] = "bozyazi"
                it[priceDate] = "5 Kasım 2022 Cumartesi"
                it[lastUpdatedTime] = System.currentTimeMillis()
                it[name] = "Salatalık"
                it[icon] = "https://cdn-icons-png.flaticon.com/512/2346/2346905.png"
                it[measure] = "Kilogram"
                it[pricePrimary] = "06.00₺"
                it[priceSecondary] = null
            }
            PriceTable.insert {
                it[id] = "5 Kasım 2022 Cumartesi - Fasulye - 12.00₺"
                it[cityId] = "bozyazi"
                it[priceDate] = "5 Kasım 2022 Cumartesi"
                it[lastUpdatedTime] = System.currentTimeMillis()
                it[name] = "Fasulye"
                it[icon] = "https://cdn1.iconfinder.com/data/icons/food-2-23/50/125-512.png"
                it[measure] = "Kilogram"
                it[pricePrimary] = "12.00₺"
                it[priceSecondary] = null
            }
            PriceTable.insert {
                it[id] = "5 Kasım 2022 Cumartesi - Kıl Biber - 12.00₺"
                it[cityId] = "bozyazi"
                it[priceDate] = "5 Kasım 2022 Cumartesi"
                it[lastUpdatedTime] = System.currentTimeMillis()
                it[name] = "Kıl Biber"
                it[icon] = "https://cdn-icons-png.flaticon.com/512/2909/2909883.png"
                it[measure] = "Kilogram"
                it[pricePrimary] = "12.00₺"
                it[priceSecondary] = null
            }
            PriceTable.insert {
                it[id] = "5 Kasım 2022 Cumartesi - Sivri Biber - 08.00"
                it[cityId] = "bozyazi"
                it[priceDate] = "5 Kasım 2022 Cumartesi"
                it[lastUpdatedTime] = System.currentTimeMillis()
                it[name] = "Sivri Biber"
                it[icon] = "https://cdn-icons-png.flaticon.com/512/2909/2909883.png"
                it[measure] = "Kilogram"
                it[pricePrimary] = "08.00₺"
                it[priceSecondary] = null
            }
        }*/
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