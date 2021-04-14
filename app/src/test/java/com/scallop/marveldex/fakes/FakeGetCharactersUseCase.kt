package com.scallop.marveldex.fakes

import com.scallop.marveldex.domain.entities.MarvelCharacterEntity
import com.scallop.marveldex.domain.entities.ResultWrapperEntity
import com.scallop.marveldex.domain.usecases.GetCharactersBaseUseCase
import com.scallop.marveldex.domain.usecases.GetCharactersUseCase
import com.scallop.marveldex.utils.Status
import com.scallop.randomweather.utils.TestUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeGetCharactersUseCase(val status: Status) :
    GetCharactersBaseUseCase {

    private fun execute(params: GetCharactersUseCase.Params): Flow<ResultWrapperEntity<List<MarvelCharacterEntity>>> =
        flow {
            when (status) {
                Status.SUCCESSFUL -> emit(ResultWrapperEntity.Success(TestUtils.getCharacters(params.limit)))
                else -> throw Exception("Something went wrong")
            }
        }

    override suspend fun invoke(params: GetCharactersUseCase.Params): Flow<ResultWrapperEntity<List<MarvelCharacterEntity>>> {
        return execute(params)
    }

}