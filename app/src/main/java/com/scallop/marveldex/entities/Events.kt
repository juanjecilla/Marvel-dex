package com.scallop.marveldex.entities

data class Events(
    val collectionURI: String,
    val available: Int,
    val returned: Int,
    val items: List<ResourceItem>
)