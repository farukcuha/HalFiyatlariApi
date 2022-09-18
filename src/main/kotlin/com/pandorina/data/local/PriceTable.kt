package com.pandorina.data.local

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object PriceTable: Table() {
    val id: Column<String?> = varchar("id", 100).nullable()
    val cityId: Column<String?> = varchar("city_id", 100).nullable()
    val priceDate: Column<String?> = varchar("price_date", 100).nullable()
    val lastUpdatedTime: Column<Long?> = long("last_updated_time").nullable()
    val name: Column<String?> = varchar("name", 100).nullable()
    val icon: Column<String?> = varchar("icon", 128).nullable()
    val measure: Column<String?> = varchar("measure", 100).nullable()
    val pricePrimary: Column<String?> = varchar("price_primary", 100).nullable()
    val priceSecondary: Column<String?> = varchar("price_secondary", 100).nullable()

    override val primaryKey: PrimaryKey
        get() = PrimaryKey(id)
}