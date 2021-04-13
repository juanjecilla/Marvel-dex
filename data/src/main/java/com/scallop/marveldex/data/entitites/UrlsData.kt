package com.scallop.marveldex.data.entitites

import com.squareup.moshi.Json

data class UrlsData(

    @Json(name = "type")
    val type: String,

    @Json(name = "url")
    val url: String
)