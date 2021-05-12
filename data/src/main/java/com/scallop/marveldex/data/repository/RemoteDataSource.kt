package com.scallop.marveldex.data.repository

import com.scallop.marveldex.data.entitites.MarvelCharacterData
import com.scallop.marveldex.data.entitites.ResultWrapperData
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    suspend fun getCharacters(limit: Int, offset: Int): Flow<ResultWrapperData<List<MarvelCharacterData>>>
    suspend fun getCharacter(id: Int): Flow<ResultWrapperData<MarvelCharacterData>>

}
