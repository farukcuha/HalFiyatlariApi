package com.pandorina.data.local.photo

import com.pandorina.data.local.city.CityTable
import com.pandorina.domain.model.Photo
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction


object PhotosDataSource{
    fun getPhoto(): Photo {
        return transaction {
            PhotosTable.selectAll().map { it.toPhoto() }.random()
        }
    }

    private fun ResultRow.toPhoto(): Photo {
        return Photo(
            title = this[PhotosTable.title],
            imageUrl = this[PhotosTable.imageUrl],
        )
    }
}