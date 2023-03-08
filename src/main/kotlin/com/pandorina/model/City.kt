package com.pandorina.model

@kotlinx.serialization.Serializable
data class City(
    val id: String?,
    val title: String?,
    val imageUrl: String?,
    val srcUrl: String?
)
