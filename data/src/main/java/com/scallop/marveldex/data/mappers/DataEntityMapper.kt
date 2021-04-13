package com.scallop.marveldex.data.mappers

import com.scallop.marveldex.data.entitites.*
import com.scallop.marveldex.domain.entities.*

class DataEntityMapper {

    fun mapCharacters(results: List<MarvelCharacterData>) = results.map { mapCharacter(it) }

    fun mapCharacter(data: MarvelCharacterData) = MarvelCharacterEntity(
        thumbnail = mapThumbnail(data.thumbnail),
        urls = mapUrls(data.urls),
//        stories = mapStories(data.stories),
//        series = data.series,
//        comics = data.comics,
        name = data.name,
        description = data.description,
        modified = data.modified,
        id = data.id,
        resourceURI = data.resourceURI,
        events = mapEvent(data.events),
    )

    private fun mapThumbnail(data: ThumbnailData) = ThumbnailEntity(
        path = data.path,
        extension = data.extension,
    )

    private fun mapStories(data: List<StoryData>) = data.map { mapStory(it) }

    private fun mapStory(data: StoryData) = StoryEntity(
        collectionURI = data.collectionURI,
        available = data.available,
        returned = data.returned,
        items = mapItems(data.items),
    )

    private fun mapEvents(data: List<EventsData>) = data.map { mapEvent(it) }

    private fun mapEvent(data: EventsData) = EventsEntity(
        collectionURI = data.collectionURI,
        available = data.available,
        returned = data.returned,
        items = mapItems(data.items),
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
}
