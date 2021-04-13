package com.scallop.marveldex.data.entitites

import com.squareup.moshi.Json

data class ResourceItemData(

    @Json(name = "name")
    val name: String,

    @Json(name = "resourceURI")
    val resourceURI: String
)