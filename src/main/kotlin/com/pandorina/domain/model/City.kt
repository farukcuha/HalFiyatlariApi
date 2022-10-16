package com.pandorina.domain.model

@kotlinx.serialization.Serializable
data class City(
    val id: String?,
    val title: String?,
    val imageUrl: String?,
    val isActive: Boolean?,
    val priority: Int?
)
