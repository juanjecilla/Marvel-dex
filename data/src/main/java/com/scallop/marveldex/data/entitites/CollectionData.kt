package com.scallop.marveldex.data.entitites

data class CollectionData(
    val collectionURI: String,
    val available: Int,
    val returned: Int,
    val items: List<ResourceItemData>
)
