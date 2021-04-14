package com.scallop.marveldex.data.repository

import com.scallop.marveldex.data.entitites.MarvelCharacterData
import com.scallop.marveldex.data.entitites.ResultWrapperData
import com.scallop.marveldex.data.mappers.DataEntityMapper
import com.scallop.marveldex.domain.entities.MarvelCharacterEntity
import com.scallop.marveldex.domain.entities.ResultWrapperEntity
import com.scallop.marveldex.domain.repositories.MarvelRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RepositoryImpl(
    private val mRemote: RemoteDataSource,
    private val mDataEntityMapper: DataEntityMapper
) : MarvelRepository {
    override suspend fun getCharacters(
        limit: Int,
        offset: Int
    ): Flow<ResultWrapperEntity<List<MarvelCharacterEntity>>> {
        return mRemote.getCharacters(limit, offset).map {
            when (it) {
                is ResultWrapperData.Success<*> -> mDataEntityMapper.mapResults(it as ResultWrapperData.Success<List<MarvelCharacterData>>)
                is ResultWrapperData.GenericError -> mDataEntityMapper.mapException(it)
                else -> throw IllegalArgumentException()
            }
        }
    }

    override suspend fun getCharacter(id: Int): Flow<ResultWrapperEntity<MarvelCharacterEntity>> {
        return mRemote.getCharacter(id).map {
            when (it) {
                is ResultWrapperData.Success<*> -> mDataEntityMapper.mapResult(it as ResultWrapperData.Success<MarvelCharacterData>)
                is ResultWrapperData.GenericError -> mDataEntityMapper.mapException(it)
                else -> throw IllegalArgumentException()
            }
        }
    }
}