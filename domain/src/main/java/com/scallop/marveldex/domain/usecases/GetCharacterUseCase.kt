package com.scallop.marveldex.domain.usecases

import com.scallop.marveldex.domain.common.BaseUseCase
import com.scallop.marveldex.domain.entities.MarvelCharacterEntity
import com.scallop.marveldex.domain.repositories.MarvelRepository
import kotlinx.coroutines.flow.Flow

typealias GetCharacterBaseUseCase = BaseUseCase<GetCharacterUseCase.Params, Flow<MarvelCharacterEntity>>

class GetCharacterUseCase(
    private val mRepository: MarvelRepository
) : GetCharacterBaseUseCase {

    override suspend fun invoke(params: Params) = mRepository.getCharacter(params.id)


    data class Params(
        val id: Int
    )
}