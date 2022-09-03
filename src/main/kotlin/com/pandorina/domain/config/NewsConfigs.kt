package com.pandorina.domain.config


const val URL_SON_DAKIKA = "https://www.sondakika.com"
@kotlinx.serialization.Serializable
data class NewsConfigModel(
    val id: Int,
    val title: String,
    val srcUrl: String,
)

sealed class NewsConfig(
    val id: Int,
    val title: String,
    val srcUrl: String,
){
    object Guncel: NewsConfig(
        id = 1,
        title = "Güncel",
        srcUrl = "$URL_SON_DAKIKA/guncel"
    )

    object Politika: NewsConfig(
        id = 2,
        title = "Politika",
        srcUrl = "$URL_SON_DAKIKA/politika"
    )

    object Ekonomi: NewsConfig(
        id = 3,
        title = "Ekonomi",
        srcUrl = "$URL_SON_DAKIKA/ekonomi"
    )

    object Spor: NewsConfig(
        id = 4,
        title = "Spor",
        srcUrl = "$URL_SON_DAKIKA/spor"
    )

    object Dunya: NewsConfig(
        id = 5,
        title = "Dünya",
        srcUrl = "$URL_SON_DAKIKA/dunya"
    )

    object Yerel: NewsConfig(
        id = 6,
        title = "Yerel",
        srcUrl = "$URL_SON_DAKIKA/yerel"
    )
    fun toNewsConfigModel(): NewsConfigModel{
        return NewsConfigModel(
            id = id,
            title = title,
            srcUrl = srcUrl
        )
    }
}