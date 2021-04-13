package com.scallop.marveldex.domain.usecases

import com.scallop.marveldex.domain.common.BaseUseCase
import com.scallop.marveldex.domain.entities.MarvelCharacterEntity
import com.scallop.marveldex.domain.repositories.MarvelRepository
import kotlinx.coroutines.flow.Flow

typealias GetCharactersBaseUseCase = BaseUseCase<GetCharactersUseCase.Params, Flow<List<MarvelCharacterEntity>>>

class GetCharactersUseCase(
    private val mRepository: MarvelRepository
) : GetCharactersBaseUseCase {

    override suspend fun invoke(params: Params) =
        mRepository.getCharacters(params.limit, params.offset)

    data class Params(
        val limit: Int,
        val offset: Int
    )
}