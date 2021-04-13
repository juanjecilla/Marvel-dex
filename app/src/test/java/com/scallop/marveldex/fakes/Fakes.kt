package com.scallop.marveldex.fakes

import com.scallop.marveldex.data.entitites.MarvelCharacterData
import com.scallop.marveldex.data.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRemoteDataSource : RemoteDataSource {
    override suspend fun getCharacters(limit: Int, offset: Int): Flow<List<MarvelCharacterData>> =
        flow {

        }

    override suspend fun getCharacter(id: Int): Flow<MarvelCharacterData> = flow {

    }
}