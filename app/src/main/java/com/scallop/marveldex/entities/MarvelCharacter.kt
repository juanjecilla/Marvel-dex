package com.scallop.marveldex.entities

data class MarvelCharacter(
    val thumbnail: Thumbnail,
    val urls: List<Urls>,
    val stories: Collection,
    val series: Collection,
    val comics: Collection,
    val name: String,
    val description: String,
    val modified: String,
    val id: Int,
    val resourceURI: String,
    val events: Collection
)