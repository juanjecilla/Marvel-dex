package com.scallop.marveldex.mappers

import com.scallop.marveldex.domain.entities.*
import com.scallop.marveldex.entities.*
import com.scallop.marveldex.entities.Collection

class CharacterMapper {

    fun mapResults(entity: ResultWrapperEntity.Success<List<MarvelCharacterEntity>>) =
        ResultWrapperEntity.Success(
            mapCharacters(entity.value)
        )

    fun mapResult(entity: ResultWrapperEntity.Success<MarvelCharacterEntity>) =
        ResultWrapperEntity.Success(
            mapCharacter(entity.value)
        )

    fun mapCharacters(entities: List<MarvelCharacterEntity>) = entities.map { mapCharacter(it) }

    fun mapCharacter(entity: MarvelCharacterEntity) = MarvelCharacter(
        thumbnail = mapThumbnail(entity.thumbnail),
        urls = mapUrls(entity.urls),
        stories = mapCollection(entity.stories),
        series = mapCollection(entity.series),
        comics = mapCollection(entity.comics),
        name = entity.name,
        description = entity.description,
        modified = entity.modified,
        id = entity.id,
        resourceURI = entity.resourceURI,
        events = mapCollection(entity.events),
    )

    private fun mapThumbnail(entity: ThumbnailEntity) = Thumbnail(
        path = entity.path,
        extension = entity.extension,
    )

    private fun mapItems(entities: List<ResourceItemEntity>) = entities.map { mapItem(it) }

    private fun mapItem(entity: ResourceItemEntity) = ResourceItem(
        name = entity.name,
        resourceURI = entity.resourceURI,
    )

    private fun mapUrls(entities: List<UrlsEntity>) = entities.map { mapUrl(it) }

    private fun mapUrl(entity: UrlsEntity) = Urls(
        type = entity.type,
        url = entity.url,
    )

    private fun mapCollection(entity: CollectionEntity) = Collection(
        collectionURI = entity.collectionURI,
        available = entity.available,
        returned = entity.returned,
        items = mapItems(entity.items),
    )
}
