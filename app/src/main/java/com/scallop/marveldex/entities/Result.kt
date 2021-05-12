package com.scallop.marveldex.entities


data class Result(
    val total: Int,
    val offset: Int,
    val limit: Int,
    val count: Int,
    val results: List<MarvelCharacter>
)
