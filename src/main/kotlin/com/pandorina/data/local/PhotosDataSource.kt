package com.pandorina.data.local

@kotlinx.serialization.Serializable
data class Photo(
    val imageUrl: String,
    val title: String
)

val photos = listOf(
    Photo(
        "https://firebasestorage.googleapis.com/v0/b/halfiyatlari-511c9.appspot.com/o/home_photos%2Fadrasan_meydan-kumluca.jpg?alt=media&token=77e9331b-3a52-426e-acde-901947531785",
        "Adrasan / Kumluca"
    ),
    Photo(
        "https://firebasestorage.googleapis.com/v0/b/halfiyatlari-511c9.appspot.com/o/home_photos%2Falanya_castle-alanya.jpg?alt=media&token=ae6e5dfd-9a8f-4a1c-a3f0-24170c6073e0",
        "Alanya Kalesi / Alanya"
    ),
    Photo(
        "https://firebasestorage.googleapis.com/v0/b/halfiyatlari-511c9.appspot.com/o/home_photos%2Falanya_castle_2-alanya.jpg?alt=media&token=3f07cbe2-3579-4efd-989d-e4c7e5a2005c",
        "Alanya Kalesi / Alanya"
    ),
    Photo(
        "https://firebasestorage.googleapis.com/v0/b/halfiyatlari-511c9.appspot.com/o/home_photos%2Falone_island-tekmen-tekeli.jpeg?alt=media&token=0fcc4853-a112-43d5-9a5a-9c275c47e136",
        "Ada / Tekmen-Bozyazı"
    ),
    Photo(
        "https://firebasestorage.googleapis.com/v0/b/halfiyatlari-511c9.appspot.com/o/home_photos%2Faspendos-serik.jpg?alt=media&token=db9fc2c9-fc04-410f-b061-7ff88451da27",
        "Aspendos / Serik"
    ),
    Photo(
        "https://firebasestorage.googleapis.com/v0/b/halfiyatlari-511c9.appspot.com/o/home_photos%2Faydincik-mersin.jpg?alt=media&token=c51c41f0-38ed-4d8e-95b6-a707cdbc6d25",
        "Ada / Aydıncık-Mersin"
    ),
    Photo(
        "https://firebasestorage.googleapis.com/v0/b/halfiyatlari-511c9.appspot.com/o/home_photos%2Fbozyazi_nehir-bozyaz%C4%B1.jpeg?alt=media&token=d3cd4866-3488-453c-9630-c3a4a4fd6ef2",
        "Sini Çayı / Bozyazı"
    ),
    Photo(
        "https://firebasestorage.googleapis.com/v0/b/halfiyatlari-511c9.appspot.com/o/home_photos%2Fcape_gelidonya-kumluca-antalya.jpg?alt=media&token=26d2c460-1b4d-4f6f-abb9-636175b85acd",
        "Cape Galedonya / Kumluca"
    ),
    Photo(
        "https://firebasestorage.googleapis.com/v0/b/halfiyatlari-511c9.appspot.com/o/home_photos%2Fcape_gelidonya_2-kumluca-antalya.jpg?alt=media&token=3365ad47-48fb-4f17-b1f7-a2c99f6687da",
        "Cape Galedonya / Kumluca"
    ),
    Photo(
        "https://firebasestorage.googleapis.com/v0/b/halfiyatlari-511c9.appspot.com/o/home_photos%2Fcoast_alanya.jpg?alt=media&token=0fdb5eac-4c62-41d6-842e-78e3a75c7aa2",
        "Alanya Sahili / Alanya"
    ),
    Photo(
        "https://firebasestorage.googleapis.com/v0/b/halfiyatlari-511c9.appspot.com/o/home_photos%2Fdemre_cayi-antalya.jpg?alt=media&token=c685c680-76c1-4921-a721-1809c64e18c1",
        "Demre Açyı / Demre"
    ),
    Photo(
        "https://firebasestorage.googleapis.com/v0/b/halfiyatlari-511c9.appspot.com/o/home_photos%2Fgirl_towe-istanbul.jpg?alt=media&token=67fac489-f8d0-49ec-800e-26729520b241",
        "Kız Kulesi / İstanbul"
    ),
    Photo(
        "https://firebasestorage.googleapis.com/v0/b/halfiyatlari-511c9.appspot.com/o/home_photos%2Fkekova-demre-antalya.jpg?alt=media&token=4749afb5-850b-46d6-8cb5-6bc6bd15ce30",
        "Kekova / Demre"
    ),
    Photo(
        "https://firebasestorage.googleapis.com/v0/b/halfiyatlari-511c9.appspot.com/o/home_photos%2Fkorfez-tekeli.jpeg?alt=media&token=b490a4a0-8691-41c9-95f6-9e5074b3d1e5",
        "Tekmen-Tekeli"
    ),
    Photo(
        "https://firebasestorage.googleapis.com/v0/b/halfiyatlari-511c9.appspot.com/o/home_photos%2Flighthouse-alanya.jpg?alt=media&token=39fe638b-c0b0-495a-8a99-d217805541ee",
        "Deniz Feneri / Alanya"
    ),
    Photo(
        "https://firebasestorage.googleapis.com/v0/b/halfiyatlari-511c9.appspot.com/o/home_photos%2Fnight-tekmen-tekeli.jpeg?alt=media&token=e5c6d783-afcb-497c-88a5-0e0e2aca2ea2",
        "Gece Isışkları / Tekmen-Tekeli"
    ),
    Photo(
        "https://firebasestorage.googleapis.com/v0/b/halfiyatlari-511c9.appspot.com/o/home_photos%2Fortako%CC%88y_mosque-istanbul.jpg?alt=media&token=2696c275-98ff-43da-ad65-f8ce8ff9734e",
        "Ortaköy Camii / İstanbul"
    ),
    Photo(
        "https://firebasestorage.googleapis.com/v0/b/halfiyatlari-511c9.appspot.com/o/home_photos%2Fsakl%C4%B1su-finike.jpg?alt=media&token=a9b04a12-85f9-4557-8898-f5951be1c0ef",
        "Saklısu / Finike"
    ),
    Photo(
        "https://firebasestorage.googleapis.com/v0/b/halfiyatlari-511c9.appspot.com/o/home_photos%2Fsunset-gazipas%CC%A7a.jpg?alt=media&token=8dde1ebd-b4cf-4edd-a08a-22b11ebd646e",
        "Gün Batımı / Gazipaşa"
    ),
    Photo(
        "https://firebasestorage.googleapis.com/v0/b/halfiyatlari-511c9.appspot.com/o/home_photos%2Fsunset-tekmen-tekeli.jpeg?alt=media&token=8a5cc7a1-58b8-4a07-b532-90a1f273c370",
        "Gün Batımı / Tekmen-Tekeli"
    ),
    Photo(
        "https://firebasestorage.googleapis.com/v0/b/halfiyatlari-511c9.appspot.com/o/home_photos%2Fturkish_flag-tekmen.jpeg?alt=media&token=b6597854-16bd-4ef7-be46-8298303028bb",
        "Türk Bayrağı / Tekmen/Bozyazı"
    ),
    Photo(
        "https://firebasestorage.googleapis.com/v0/b/halfiyatlari-511c9.appspot.com/o/home_photos%2Fzeytin_kayasi-tekmen.jpeg?alt=media&token=8866111d-7587-414b-9a05-5ee9034639d3",
        "Zeytin Kayası / Tekmen/Bozyazı"
    )
)