package com.pandorina.domain.model.dto

data class FirestoreDto(val writes: List<Write>)

data class Write(val update: Update)

data class Update(
    val fields: Any,
    val name: String
)

