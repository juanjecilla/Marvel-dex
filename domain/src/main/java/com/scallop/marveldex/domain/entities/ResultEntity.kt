package com.scallop.marveldex.domain.entities


data class ResultEntity(
    val total: Int,
    val offset: Int,
    val limit: Int,
    val count: Int,
    val results: List<MarvelCharacterEntity>
)
