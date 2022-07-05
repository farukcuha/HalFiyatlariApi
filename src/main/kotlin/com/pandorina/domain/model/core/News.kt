package com.pandorina.domain.model.core

@kotlinx.serialization.Serializable
data class News(
    val time: String?,
    val path: String?,
    val image: String?,
    val title: String?,
    val content: String?
)