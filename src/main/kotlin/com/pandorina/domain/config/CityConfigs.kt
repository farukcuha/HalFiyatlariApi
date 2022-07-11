package com.pandorina.domain.config

const val URL_GUNCEL_HAL_FIYATLAR = "https://www.guncelfiyatlari.com"
const val URL_CITY_IMAGES_FIREBASE_STORAGE =
    "https://firebasestorage.googleapis.com/v0/b/halfiyatlari-511c9.appspot.com/o/city_images%2F"

@kotlinx.serialization.Serializable
data class CityConfigModel(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val srcUrl: String
)

sealed class CityConfig(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val srcUrl: String
) {
    object Kumluca : CityConfig(
        id = 1,
        title = "Kumluca",
        imageUrl = "${URL_CITY_IMAGES_FIREBASE_STORAGE}kumluca.jpg?alt=media&token=1dc7a535-8ef0-443a-a700-792da1b411a7",
        srcUrl = "$URL_GUNCEL_HAL_FIYATLAR/kumluca-hal-fiyatlari"
    )

    object Demre : CityConfig(
        id = 2,
        title = "Demre",
        imageUrl = "${URL_CITY_IMAGES_FIREBASE_STORAGE}demre.jpg?alt=media&token=9a814fac-24b0-47e7-a84d-359a73b20b2e",
        srcUrl = "$URL_GUNCEL_HAL_FIYATLAR/demre-hal-fiyatlari"
    )

    object Finike : CityConfig(
        id = 3,
        title = "Finike",
        imageUrl = "${URL_CITY_IMAGES_FIREBASE_STORAGE}finike.jpg?alt=media&token=74f12e14-f15c-4840-8d3c-de9e09f03daf",
        srcUrl = "$URL_GUNCEL_HAL_FIYATLAR/finike-hal-fiyatlari"
    )

    object Alanya : CityConfig(
        id = 4,
        title = "Alanya",
        imageUrl = "${URL_CITY_IMAGES_FIREBASE_STORAGE}alanya.jpg?alt=media&token=55e706bc-1a2f-4c30-9ff6-173aa0aad663",
        srcUrl = "$URL_GUNCEL_HAL_FIYATLAR/alanya-hal-fiyatlari"
    )

    object Serik : CityConfig(
        id = 5,
        title = "Serik",
        imageUrl = "${URL_CITY_IMAGES_FIREBASE_STORAGE}serik.jpeg?alt=media&token=12bf4d45-1e1f-432e-a26e-7f06ae22ea33",
        srcUrl = "$URL_GUNCEL_HAL_FIYATLAR/serik-hal-fiyatlari"
    )

    object Gazipasa : CityConfig(
        id = 6,
        title = "Gazipaşa",
        imageUrl = "${URL_CITY_IMAGES_FIREBASE_STORAGE}gazipasa.jpeg?alt=media&token=a800f68c-f118-42c9-a02b-afeda8b4b607",
        srcUrl = "$URL_GUNCEL_HAL_FIYATLAR/gazipasa-hal-fiyatlari"
    )

    object Antalya : CityConfig(
        id = 7,
        title = "Antalya",
        imageUrl = "${URL_CITY_IMAGES_FIREBASE_STORAGE}antalya.jpg?alt=media&token=66f0107e-a920-4462-b206-d94967d9f730",
        srcUrl = "$URL_GUNCEL_HAL_FIYATLAR/antalya-hal-fiyatlari"
    )

    object Istanbul : CityConfig(
        id = 8,
        title = "İstanbul",
        imageUrl = "${URL_CITY_IMAGES_FIREBASE_STORAGE}istanbul.jpg?alt=media&token=b76c85a2-62f9-4b08-8abb-5bbcb1c7b8f9",
        srcUrl = "$URL_GUNCEL_HAL_FIYATLAR/istanbul-hal-fiyatlari"
    )

    object Bozyazi : CityConfig(
        id = 8,
        title = "Tekmen - Tekeli - Bozyazı",
        imageUrl = "${URL_CITY_IMAGES_FIREBASE_STORAGE}bozyazi.jpeg?alt=media&token=f1655d9a-e7d4-4ef3-a8b3-82a169a8f1fd",
        srcUrl = "http://www.tekeliajans.net"
    )
}



