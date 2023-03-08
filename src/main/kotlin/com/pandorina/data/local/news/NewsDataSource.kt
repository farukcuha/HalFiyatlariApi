package com.pandorina.data.local.news

object NewsDataSource {
    val categories = listOf(
        NewsConfig.Guncel.toNewsConfigModel(),
        NewsConfig.Politika.toNewsConfigModel(),
        NewsConfig.Ekonomi.toNewsConfigModel(),
        NewsConfig.Spor.toNewsConfigModel(),
        NewsConfig.Dunya.toNewsConfigModel(),
        NewsConfig.Yerel.toNewsConfigModel()
    )
}