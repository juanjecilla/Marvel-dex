package com.scallop.marveldex.data.entitites

import com.squareup.moshi.Json

data class ResultData(

    @Json(name = "total")
    val total: Int,

    @Json(name = "offset")
    val offset: Int,

    @Json(name = "limit")
    val limit: Int,

    @Json(name = "count")
    val count: Int,

    @Json(name = "results")
    val results: List<MarvelCharacterData>
)
