package com.scallop.marveldex.data.entitites

import com.squareup.moshi.Json

data class ThumbnailData(

    @Json(name = "path")
    val path: String,

    @Json(name = "extension")
    val extension: String
)