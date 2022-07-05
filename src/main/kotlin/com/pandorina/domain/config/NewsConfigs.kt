package com.pandorina.domain.config

import com.pandorina.domain.model.config.NewsConfig

const val URL_SON_DAKIKA = "https://www.sondakika.com"

val guncel = NewsConfig(
    id = 1,
    title = "Güncel",
    srcUrl = "$URL_SON_DAKIKA/guncel"
)

val politika = NewsConfig(
    id = 2,
    title = "Politika",
    srcUrl = "$URL_SON_DAKIKA/politika"
)

val ekonomi = NewsConfig(
    id = 3,
    title = "Ekonomi",
    srcUrl = "$URL_SON_DAKIKA/ekonomi"
)

val spor = NewsConfig(
    id = 4,
    title = "Spor",
    srcUrl = "$URL_SON_DAKIKA/spor"
)

val dunya = NewsConfig(
    id = 5,
    title = "Dünya",
    srcUrl = "$URL_SON_DAKIKA/dunya"
)

val yerel = NewsConfig(
    id = 6,
    title = "Yerel",
    srcUrl = "$URL_SON_DAKIKA/yerel"
)