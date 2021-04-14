package com.scallop.marveldex.data.repository

import com.scallop.marveldex.data.api.MarvelApi
import com.scallop.marveldex.data.entitites.MarvelCharacterData
import com.scallop.marveldex.data.entitites.ResultWrapperData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class RemoteDataSourceImpl(private val mApi: MarvelApi) : RemoteDataSource {
    override suspend fun getCharacters(limit: Int, offset: Int): Flow<ResultWrapperData<List<MarvelCharacterData>>> {
        return flow {
            try {
                val results = mApi.getCharacters(limit, offset)
                emit(ResultWrapperData.Success(results.data.results))
            } catch (exception: HttpException){
                emit(ResultWrapperData.GenericError(exception.code(), exception))
            } catch (exception: Exception){
                emit(ResultWrapperData.GenericError(exception = exception))
            }
        }
    }

    override suspend fun getCharacter(id: Int): Flow<ResultWrapperData<MarvelCharacterData>> {
        return flow {
            try {
                val results = mApi.getCharacter(id)
                emit(ResultWrapperData.Success(results.data.results.first()))
            } catch (exception: HttpException){
                emit(ResultWrapperData.GenericError(exception.code(), exception))
            } catch (exception: Exception){
                emit(ResultWrapperData.GenericError(exception = exception))
            }
        }
    }
}