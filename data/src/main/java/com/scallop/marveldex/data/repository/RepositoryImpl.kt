package com.scallop.marveldex.data.repository

import com.scallop.marveldex.data.mappers.DataEntityMapper
import com.scallop.marveldex.domain.entities.MarvelCharacterEntity
import com.scallop.marveldex.domain.repositories.MarvelRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RepositoryImpl(
    private val mRemote: RemoteDataSource,
    private val mDataEntityMapper: DataEntityMapper
) : MarvelRepository {
    override suspend fun getCharacters(limit: Int, offset: Int): Flow<List<MarvelCharacterEntity>> {
        return mRemote.getCharacters(limit, offset).map { mDataEntityMapper.mapCharacters(it) }
    }

    override suspend fun getCharacter(id: Int): Flow<MarvelCharacterEntity> {
        return mRemote.getCharacter(id).map { mDataEntityMapper.mapCharacter(it) }
    }
}