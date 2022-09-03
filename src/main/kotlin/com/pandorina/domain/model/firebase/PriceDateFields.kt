package com.pandorina.domain.model.firebase

import com.pandorina.domain.model.firebase.CityId
import com.pandorina.domain.model.firebase.LastUpdatedTime
import com.pandorina.domain.model.firebase.PriceDate

data class PriceDateFields(
    val date: PriceDate,
    val cityId: CityId,
    val lastUpdatedTime: LastUpdatedTime
)