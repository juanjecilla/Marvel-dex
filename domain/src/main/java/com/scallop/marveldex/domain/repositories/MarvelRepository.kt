package com.scallop.marveldex.domain.repositories

import com.scallop.marveldex.domain.entities.MarvelCharacterEntity
import kotlinx.coroutines.flow.Flow

interface MarvelRepository {

    suspend fun getCharacters(limit: Int, offset: Int): Flow<List<MarvelCharacterEntity>>
    suspend fun getCharacter(id: Int): Flow<MarvelCharacterEntity>
}