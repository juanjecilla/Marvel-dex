package com.scallop.marveldex.data.entitites

import com.squareup.moshi.Json

data class MarvelApiRequest(

    @Json(name = "copyright")
    val copyright: String,

    @Json(name = "code")
    val code: Int,

    @Json(name = "data")
    val data: ResultData,

    @Json(name = "attributionHTML")
    val attributionHTML: String,

    @Json(name = "attributionText")
    val attributionText: String,

    @Json(name = "etag")
    val etag: String,

    @Json(name = "status")
    val status: String
)