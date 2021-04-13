package com.scallop.marveldex.entities

data class MarvelCharacter(
    val thumbnail: Thumbnail,
    val urls: List<Urls>,
//	val stories: StoriesEntity,
//	val series: SeriesEntity,
//	val comics: ComicsEntity,
    val name: String,
    val description: String,
    val modified: String,
    val id: Int,
    val resourceURI: String,
    val events: Events
)