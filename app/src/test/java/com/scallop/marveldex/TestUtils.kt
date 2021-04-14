package com.scallop.marveldex

import com.scallop.marveldex.domain.entities.CollectionEntity
import com.scallop.marveldex.domain.entities.MarvelCharacterEntity
import com.scallop.marveldex.domain.entities.ThumbnailEntity

object TestUtils {

    fun getCharacters(size: Int): List<MarvelCharacterEntity> {
        val characters = mutableListOf<MarvelCharacterEntity>()

        for (i in 0 until size) {
            characters.add(getCharacter(i))
        }
        return characters
    }

    private fun getCharacter(i: Int) = MarvelCharacterEntity(
        ThumbnailEntity("$i", "$i"),
        listOf(),
        CollectionEntity("stories $i", i, i, listOf()),
        CollectionEntity("series $i", i, i, listOf()),
        CollectionEntity("comics $i", i, i, listOf()),
        "name $i",
        "description $i",
        "modified $i",
        i,
        "resourceURI $i",
        CollectionEntity("events $i", i, i, listOf()),
    )
}