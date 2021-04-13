package com.scallop.marveldex.entities

data class Stories(

    val collectionURI: String,
    val available: Int,
    val returned: Int,
    val items: List<ResourceItem>
)