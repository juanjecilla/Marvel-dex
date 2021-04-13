package com.scallop.marveldex.data.entitites

import com.squareup.moshi.Json

data class EventsData(

    @Json(name = "collectionURI")
    val collectionURI: String,

    @Json(name = "available")
    val available: Int,

    @Json(name = "returned")
    val returned: Int,

    @Json(name = "items")
    val items: List<ResourceItemData>
)