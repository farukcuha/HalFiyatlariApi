package com.pandorina.data.local

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table


object PriceTable: Table() {
    val id: Column<String?> = varchar("id", 100).nullable()
    val cityId: Column<Int?> = integer("city_id").nullable()
    val priceDate: Column<String?> = varchar("priceDate", 100).nullable()
    val lastUpdatedTime: Column<Long?> = long("last_updated_time").nullable()
    val name: Column<String?> = varchar("name", 100).nullable()
    val icon: Column<String?> = varchar("icon", 100).nullable()
    val measure: Column<String?> = varchar("measure", 100).nullable()
    val pricePrimary: Column<String?> = varchar("price_1", 100).nullable()
    val priceSecondary: Column<String?> = varchar("price_2", 100).nullable()

    override val primaryKey: PrimaryKey
        get() = PrimaryKey(id)
}