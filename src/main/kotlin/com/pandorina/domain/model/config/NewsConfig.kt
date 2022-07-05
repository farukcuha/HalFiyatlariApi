package com.pandorina.domain.model.config

@kotlinx.serialization.Serializable
data class NewsConfig(
    val id: Int,
    val title: String,
    val srcUrl: String,
)
