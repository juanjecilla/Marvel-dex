package com.scallop.marveldex.data.mappers

import com.scallop.marveldex.data.entitites.*
import com.scallop.marveldex.domain.entities.*

class DataEntityMapper {

    fun mapResults(data: ResultWrapperData.Success<List<MarvelCharacterData>>) =
        ResultWrapperEntity.Success(
            mapCharacters(data.value)
        )

    fun mapResult(data: ResultWrapperData.Success<MarvelCharacterData>) =
        ResultWrapperEntity.Success(
            mapCharacter(data.value)
        )

    fun mapException(data: ResultWrapperData.GenericError) = ResultWrapperEntity.GenericError(
        data.code, data.exception
    )


    fun mapCharacters(results: List<MarvelCharacterData>) = results.map { mapCharacter(it) }

    fun mapCharacter(data: MarvelCharacterData) = MarvelCharacterEntity(
        thumbnail = mapThumbnail(data.thumbnail),
        urls = mapUrls(data.urls),
        stories = mapCollection(data.stories),
        series = mapCollection(data.series),
        comics = mapCollection(data.comics),
        name = data.name,
        description = data.description,
        modified = data.modified,
        id = data.id,
        resourceURI = data.resourceURI,
        events = mapCollection(data.events),
    )

    private fun mapThumbnail(data: ThumbnailData) = ThumbnailEntity(
        path = data.path,
        extension = data.extension,
    )

    private fun mapItems(data: List<ResourceItemData>) = data.map { mapItem(it) }

    private fun mapItem(data: ResourceItemData) = ResourceItemEntity(
        name = data.name,
        resourceURI = data.resourceURI,
    )

    private fun mapUrls(data: List<UrlsData>) = data.map { mapUrl(it) }

    private fun mapUrl(data: UrlsData) = UrlsEntity(
        type = data.type,
        url = data.url,
    )

    private fun mapCollections(data: List<CollectionData>) = data.map { mapCollection(it) }

    private fun mapCollection(data: CollectionData) = CollectionEntity(
        collectionURI = data.collectionURI,
        available = data.available,
        returned = data.returned,
        items = mapItems(data.items),
    )
}
