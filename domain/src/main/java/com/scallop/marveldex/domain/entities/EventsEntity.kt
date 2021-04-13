package com.scallop.marveldex.domain.entities

data class EventsEntity(
    val collectionURI: String,
    val available: Int,
    val returned: Int,
    val items: List<ResourceItemEntity>
)