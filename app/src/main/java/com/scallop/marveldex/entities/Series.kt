package com.scallop.marveldex.entities


data class Series(
    val collectionURI: String,
    val available: Int,
    val returned: Int,
    val items: List<ResourceItem>
)