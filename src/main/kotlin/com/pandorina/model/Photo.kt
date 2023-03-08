package com.pandorina.model

@kotlinx.serialization.Serializable
data class Photo(
    val imageUrl: String?,
    val title: String?
)