package com.pandorina.domain.config

const val URL_GUNCEL_HAL_FIYATLAR = "https://www.guncelfiyatlari.com"

sealed class CityConfig(
    val id: String,
    val srcUrl: String
) {
    object Kumluca : CityConfig(
        id = "kumluca",
        srcUrl = "$URL_GUNCEL_HAL_FIYATLAR/kumluca-hal-fiyatlari"
    )

    object Demre : CityConfig(
        id = "demre",
        srcUrl = "$URL_GUNCEL_HAL_FIYATLAR/demre-hal-fiyatlari"
    )

    object Finike : CityConfig(
        id = "finike",
        srcUrl = "$URL_GUNCEL_HAL_FIYATLAR/finike-hal-fiyatlari"
    )

    object Alanya : CityConfig(
        id = "alanya",
        srcUrl = "$URL_GUNCEL_HAL_FIYATLAR/alanya-hal-fiyatlari"
    )

    object Serik : CityConfig(
        id = "serik",
        srcUrl = "$URL_GUNCEL_HAL_FIYATLAR/serik-hal-fiyatlari"
    )

    object Gazipasa : CityConfig(
        id = "gazipasa",
        srcUrl = "$URL_GUNCEL_HAL_FIYATLAR/gazipasa-hal-fiyatlari"
    )

    object Antalya : CityConfig(
        id = "antalya",
        srcUrl = "$URL_GUNCEL_HAL_FIYATLAR/antalya-hal-fiyatlari"
    )

    object Istanbul : CityConfig(
        id = "istanbul",
        srcUrl = "$URL_GUNCEL_HAL_FIYATLAR/istanbul-hal-fiyatlari"
    )

    object Bozyazi : CityConfig(
        id = "bozyazi",
        srcUrl = "http://www.tekeliajans.net"
    )
}



