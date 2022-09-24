package com.pandorina.data.local.city


import com.pandorina.domain.model.City
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object CitiesDataSource {
    fun getCities(): List<City> {
        return transaction {
            CityTable.selectAll()
                .orderBy(CityTable.priority, SortOrder.DESC)
                .map { it.toCity() }
        }
    }

    private fun ResultRow.toCity(): City {
        return City(
            id = this[CityTable.id],
            title = this[CityTable.title],
            imageUrl = this[CityTable.imageUrl],
            isActive = this[CityTable.isActive],
            priority = this[CityTable.priority],
        )
    }
}
