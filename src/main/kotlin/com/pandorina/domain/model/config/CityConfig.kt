package com.pandorina.domain.model.config

@kotlinx.serialization.Serializable
data class CityConfig(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val srcUrl: String
)