package com.pandorina.domain.config

fun CityConfig.toCityConfigModel(): CityConfigModel{
    return CityConfigModel(
        id = id,
        title = title,
        imageUrl = imageUrl,
        srcUrl = srcUrl
    )
}

fun NewsConfig.toNewsConfigModel(): NewsConfigModel{
    return NewsConfigModel(
        id = id,
        title = title,
        srcUrl = srcUrl
    )
}