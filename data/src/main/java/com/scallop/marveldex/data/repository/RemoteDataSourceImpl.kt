package com.scallop.marveldex.data.repository

import com.scallop.marveldex.data.api.MarvelApi
import com.scallop.marveldex.data.entitites.MarvelCharacterData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteDataSourceImpl(private val mApi: MarvelApi) : RemoteDataSource {
    override suspend fun getCharacters(limit: Int, offset: Int): Flow<List<MarvelCharacterData>> {
        return flow {
            val results = mApi.getCharacters(limit, offset)
            emit(results.data.results)
        }
    }

    override suspend fun getCharacter(id: Int): Flow<MarvelCharacterData> {
        return flow {
            val results = mApi.getCharacter(id)
            emit(results.data.results.first())
        }
    }
}