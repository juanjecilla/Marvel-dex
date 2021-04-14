package com.scallop.marveldex.domain.entities

data class MarvelCharacterEntity(
	val thumbnail: ThumbnailEntity,
	val urls: List<UrlsEntity>,
	val stories: CollectionEntity,
	val series: CollectionEntity,
	val comics: CollectionEntity,
	val name: String,
	val description: String,
	val modified: String,
	val id: Int,
	val resourceURI: String,
	val events: CollectionEntity
)