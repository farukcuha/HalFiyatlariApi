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
//        val date = "1 Aralık 2022 Perşembe"
//        val pricePatlican = "6.00₺"
//        val priceSalatalik = "7.50₺"
//        val priceFasulye = "13.00₺"
//        val priceKılbiber = "15.00₺"
//        val priceSivriBiber = "09.00₺"
//        transaction {
//            PriceTable.insert {
//                it[id] = "$date - Patlıcan - $pricePatlican"
//                it[cityId] = "bozyazi"
//                it[priceDate] = date
//                it[lastUpdatedTime] = System.currentTimeMillis()
//                it[name] = "Patlıcan"
//                it[icon] = "https://cdn-icons-png.flaticon.com/512/766/766026.png"
//                it[measure] = "Kilogram"
//                it[pricePrimary] = pricePatlican
//                it[priceSecondary] = null
//            }
//            PriceTable.insert {
//                it[id] = "$date - Salatalık - $priceSalatalik"
//                it[cityId] = "bozyazi"
//                it[priceDate] = date
//                it[lastUpdatedTime] = System.currentTimeMillis()
//                it[name] = "Salatalık"
//                it[icon] = "https://cdn-icons-png.flaticon.com/512/2346/2346905.png"
//                it[measure] = "Kilogram"
//                it[pricePrimary] = priceSalatalik
//                it[priceSecondary] = null
//            }
//            PriceTable.insert {
//                it[id] = "$date - Fasulye - $priceFasulye"
//                it[cityId] = "bozyazi"
//                it[priceDate] = date
//                it[lastUpdatedTime] = System.currentTimeMillis()
//                it[name] = "Fasulye"
//                it[icon] = "https://cdn1.iconfinder.com/data/icons/food-2-23/50/125-512.png"
//                it[measure] = "Kilogram"
//                it[pricePrimary] = priceFasulye
//                it[priceSecondary] = null
//            }
//            PriceTable.insert {
//                it[id] = "$date - Kıl Biber - $priceKılbiber"
//                it[cityId] = "bozyazi"
//                it[priceDate] = date
//                it[lastUpdatedTime] = System.currentTimeMillis()
//                it[name] = "Kıl Biber"
//                it[icon] = "https://cdn-icons-png.flaticon.com/512/2909/2909883.png"
//                it[measure] = "Kilogram"
//                it[pricePrimary] = priceKılbiber
//                it[priceSecondary] = null
//            }
//            PriceTable.insert {
//                it[id] = "$date - Sivri Biber - $priceSivriBiber"
//                it[cityId] = "bozyazi"
//                it[priceDate] = date
//                it[lastUpdatedTime] = System.currentTimeMillis()
//                it[name] = "Sivri Biber"
//                it[icon] = "https://cdn-icons-png.flaticon.com/512/2909/2909883.png"
//                it[measure] = "Kilogram"
//                it[pricePrimary] = priceSivriBiber
//                it[priceSecondary] = null
//            }
//        }
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