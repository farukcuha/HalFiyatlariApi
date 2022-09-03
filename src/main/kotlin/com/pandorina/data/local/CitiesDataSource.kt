package com.pandorina.data.local

import com.pandorina.domain.config.CityConfig

object CitiesDataSource {
    val cities = listOf(
        CityConfig.Kumluca.toNewsConfigModel(),
        CityConfig.Demre.toNewsConfigModel(),
        CityConfig.Finike.toNewsConfigModel(),
        CityConfig.Alanya.toNewsConfigModel(),
        CityConfig.Serik.toNewsConfigModel(),
        CityConfig.Gazipasa.toNewsConfigModel(),
        CityConfig.Antalya.toNewsConfigModel(),
        CityConfig.Istanbul.toNewsConfigModel(),
        CityConfig.Bozyazi.toNewsConfigModel(),
    )
}