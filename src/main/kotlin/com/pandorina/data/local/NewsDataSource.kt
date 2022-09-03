package com.pandorina.data.local

import com.pandorina.domain.config.*

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