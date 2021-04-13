package com.scallop.marveldex.mappers

import com.scallop.marveldex.domain.entities.*
import com.scallop.marveldex.entities.*

class CharacterMapper {

    fun mapCharacters(results: List<MarvelCharacterEntity>) = results.map { mapCharacter(it) }

    fun mapCharacter(data: MarvelCharacterEntity) = MarvelCharacter(
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

    private fun mapThumbnail(data: ThumbnailEntity) = Thumbnail(
        path = data.path,
        extension = data.extension,
    )

    private fun mapStories(data: List<StoryEntity>) = data.map { mapStory(it) }

    private fun mapStory(data: StoryEntity) = Stories(
        collectionURI = data.collectionURI,
        available = data.available,
        returned = data.returned,
        items = mapItems(data.items),
    )

    private fun mapEvents(data: List<EventsEntity>) = data.map { mapEvent(it) }

    private fun mapEvent(data: EventsEntity) = Events(
        collectionURI = data.collectionURI,
        available = data.available,
        returned = data.returned,
        items = mapItems(data.items),
    )

    private fun mapItems(data: List<ResourceItemEntity>) = data.map { mapItem(it) }

    private fun mapItem(data: ResourceItemEntity) = ResourceItem(
        name = data.name,
        resourceURI = data.resourceURI,
    )

    private fun mapUrls(data: List<UrlsEntity>) = data.map { mapUrl(it) }

    private fun mapUrl(data: UrlsEntity) = Urls(
        type = data.type,
        url = data.url,
    )

}
