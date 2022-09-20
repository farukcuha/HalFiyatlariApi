package com.pandorina.data.local

import com.pandorina.BASE_URL

val BASE_PHOTOS_URL = "$BASE_URL/photos"

@kotlinx.serialization.Serializable
data class Photo(
    val imageUrl: String,
    val title: String
)

object PhotosDataSource{
    val photos = listOf(
        Photo(
            "$BASE_PHOTOS_URL/adrasan_meydan-kumluca.jpg",
            "Adrasan / Kumluca"
        ),
        Photo(
            "$BASE_PHOTOS_URL/alanya_castle-alanya.jpg",
            "Alanya Kalesi / Alanya"
        ),
        Photo(
            "$BASE_PHOTOS_URL/alanya_castle_2-alanya.jpg",
            "Alanya Kalesi / Alanya"
        ),
        Photo(
            "$BASE_PHOTOS_URL/alone_island-tekmen-tekeli.jpeg",
            "Ada / Tekmen-Bozyazı"
        ),
        Photo(
            "$BASE_PHOTOS_URL/aspendos-serik.jpg",
            "Aspendos / Serik"
        ),
        Photo(
            "$BASE_PHOTOS_URL/aydincik-mersin.jpg",
            "Ada / Aydıncık-Mersin"
        ),
        Photo(
            "$BASE_PHOTOS_URL/bozyazi_nehir-bozyazı.jpg",
            "Sini Çayı / Bozyazı"
        ),
        Photo(
            "$BASE_PHOTOS_URL/cape_gelidonya_2-kumluca-antalya.jpg",
            "Cape Galedonya / Kumluca"
        ),
        Photo(
            "$BASE_PHOTOS_URL/cape_gelidonya-kumluca-antalya.jpg",
            "Cape Galedonya / Kumluca"
        ),
        Photo(
            "$BASE_PHOTOS_URL/coast_alanya.jpg",
            "Alanya Sahili / Alanya"
        ),
        Photo(
            "$BASE_PHOTOS_URL/demre_cayi-antalya.jpg",
            "Demre Çayı / Demre"
        ),
        Photo(
            "$BASE_PHOTOS_URL/girl_towe-istanbul.jpg",
            "Kız Kulesi / İstanbul"
        ),
        Photo(
            "$BASE_PHOTOS_URL/kekova-demre-antalya.jpg",
            "Kekova / Demre"
        ),
        Photo(
            "$BASE_PHOTOS_URL/korfez-tekeli.jpeg",
            "Tekmen-Tekeli"
        ),
        Photo(
            "$BASE_PHOTOS_URL/lighthouse-alanya.jpg",
            "Deniz Feneri / Alanya"
        ),
        Photo(
            "$BASE_PHOTOS_URL/night-tekmen-tekeli.jpeg",
            "Gece Isışkları / Tekmen-Tekeli"
        ),
        Photo(
            "$BASE_PHOTOS_URL/ortaköy_mosque-istanbul.jpg",
            "Ortaköy Camii / İstanbul"
        ),
        Photo(
            "$BASE_PHOTOS_URL/saklısu-finike.jpg",
            "Saklısu / Finike"
        ),
        Photo(
            "$BASE_PHOTOS_URL/sunset-gazipaşa.jpg",
            "Gün Batımı / Gazipaşa"
        ),
        Photo(
            "$BASE_PHOTOS_URL/sunset-tekmen-tekeli.jpeg",
            "Gün Batımı / Tekmen-Tekeli"
        ),
        Photo(
            "$BASE_PHOTOS_URL/turkish_flag-tekmen.jpeg",
            "Türk Bayrağı / Tekmen/Bozyazı"
        ),
        Photo(
            "$BASE_PHOTOS_URL/zeytin_kayasi-tekmen.jpeg",
            "Zeytin Kayası / Tekmen/Bozyazı"
        )
    )
}