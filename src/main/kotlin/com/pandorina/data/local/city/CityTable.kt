package com.pandorina.data.local.city

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object CityTable: Table() {
    val id: Column<String?> = varchar("id", 50).nullable()
    val priority: Column<Int?> = integer("priority").nullable()
    val imageUrl: Column<String?> = varchar("image_url", 256).nullable()
    val isActive: Column<Boolean?> = bool("is_active").nullable()
    val title: Column<String?> = varchar("title", 50).nullable()

    override val primaryKey: Table.PrimaryKey
        get() = PrimaryKey(id)
}