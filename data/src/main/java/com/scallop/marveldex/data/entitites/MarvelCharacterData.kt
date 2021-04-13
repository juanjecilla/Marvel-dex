package com.scallop.marveldex.data.entitites

import com.squareup.moshi.Json

data class MarvelCharacterData(

    @Json(name = "thumbnail")
    val thumbnail: ThumbnailData,

    @Json(name = "urls")
    val urls: List<UrlsData>,

    @Json(name = "stories")
    val stories: StoryData,

    @Json(name = "series")
    val series: SeriesData,

    @Json(name = "comics")
    val comics: ComicsData,

    @Json(name = "name")
    val name: String,

    @Json(name = "description")
    val description: String,

    @Json(name = "modified")
    val modified: String,

    @Json(name = "id")
    val id: Int,

    @Json(name = "resourceURI")
    val resourceURI: String,

    @Json(name = "events")
    val events: EventsData
)