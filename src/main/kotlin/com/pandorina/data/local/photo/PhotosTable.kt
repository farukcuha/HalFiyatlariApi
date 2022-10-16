package com.pandorina.data.local.photo

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object PhotosTable: Table() {
    val id: Column<Int> = integer("id")
    val imageUrl: Column<String?> = varchar("image_url", 256).nullable()
    val title: Column<String?> = varchar("title", 50).nullable()

    override val primaryKey: PrimaryKey
        get() = PrimaryKey(id)
}