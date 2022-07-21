package com.pandorina.domain.config


fun NewsConfig.toNewsConfigModel(): NewsConfigModel{
    return NewsConfigModel(
        id = id,
        title = title,
        srcUrl = srcUrl
    )
}