package com.pandorina.domain.config

import com.pandorina.BASE_URL

const val URL_GUNCEL_HAL_FIYATLAR = "https://www.guncelfiyatlari.com"
const val BASE_CITIES_IMAGE_URL = "$BASE_URL/cities"
@kotlinx.serialization.Serializable
data class CityConfigModel(
    val id: String,
    val title: String,
    val imageUrl: String,
    val srcUrl: String,
    val isActive: Boolean,
    val priority: Int
)

sealed class CityConfig(
    val id: String,
    val title: String,
    val imageUrl: String,
    val srcUrl: String
) {
    object Kumluca : CityConfig(
        id = "kumluca",
        title = "Kumluca",
        imageUrl = "$BASE_CITIES_IMAGE_URL/kumluca.jpg",
        srcUrl = "$URL_GUNCEL_HAL_FIYATLAR/kumluca-hal-fiyatlari"
    )

    object Demre : CityConfig(
        id = "demre",
        title = "Demre",
        imageUrl = "$BASE_CITIES_IMAGE_URL/demre.jpg",
        srcUrl = "$URL_GUNCEL_HAL_FIYATLAR/demre-hal-fiyatlari"
    )

    object Finike : CityConfig(
        id = "finike",
        title = "Finike",
        imageUrl = "$BASE_CITIES_IMAGE_URL/finike.jpg",
        srcUrl = "$URL_GUNCEL_HAL_FIYATLAR/finike-hal-fiyatlari"
    )

    object Alanya : CityConfig(
        id = "alanya",
        title = "Alanya",
        imageUrl = "$BASE_CITIES_IMAGE_URL/alanya.jpg",
        srcUrl = "$URL_GUNCEL_HAL_FIYATLAR/alanya-hal-fiyatlari"
    )

    object Serik : CityConfig(
        id = "serik",
        title = "Serik",
        imageUrl = "$BASE_CITIES_IMAGE_URL/serik.jpeg",
        srcUrl = "$URL_GUNCEL_HAL_FIYATLAR/serik-hal-fiyatlari"
    )

    object Gazipasa : CityConfig(
        id = "gazipasa",
        title = "Gazipaşa",
        imageUrl = "$BASE_CITIES_IMAGE_URL/gazipasa.jpeg",
        srcUrl = "$URL_GUNCEL_HAL_FIYATLAR/gazipasa-hal-fiyatlari"
    )

    object Antalya : CityConfig(
        id = "antalya",
        title = "Antalya",
        imageUrl = "$BASE_CITIES_IMAGE_URL/antalya.jpg",
        srcUrl = "$URL_GUNCEL_HAL_FIYATLAR/antalya-hal-fiyatlari"
    )

    object Istanbul : CityConfig(
        id = "istanbul",
        title = "İstanbul",
        imageUrl = "$BASE_CITIES_IMAGE_URL/istanbul.jpg",
        srcUrl = "$URL_GUNCEL_HAL_FIYATLAR/istanbul-hal-fiyatlari"
    )

    object Bozyazi : CityConfig(
        id = "bozyazi",
        title = "Bozyazı-Tekmen-Tekeli",
        imageUrl = "$BASE_CITIES_IMAGE_URL/bozyazi.jpeg",
        srcUrl = "http://www.tekeliajans.net"
    )
    fun toNewsConfigModel(): CityConfigModel{
        return CityConfigModel(
            id = id,
            title = title,
            imageUrl = imageUrl,
            srcUrl = srcUrl,
            isActive = true,
            priority = 0
        )
    }
}



