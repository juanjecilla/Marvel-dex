package com.scallop.marveldex.di

import com.scallop.marveldex.data.api.MarvelApi
import com.scallop.marveldex.data.mappers.DataEntityMapper
import com.scallop.marveldex.data.repository.RemoteDataSource
import com.scallop.marveldex.data.repository.RemoteDataSourceImpl
import com.scallop.marveldex.data.repository.RepositoryImpl
import com.scallop.marveldex.domain.repositories.MarvelRepository
import com.scallop.marveldex.domain.usecases.GetCharacterBaseUseCase
import com.scallop.marveldex.domain.usecases.GetCharacterUseCase
import com.scallop.marveldex.domain.usecases.GetCharactersBaseUseCase
import com.scallop.marveldex.domain.usecases.GetCharactersUseCase
import com.scallop.marveldex.mappers.CharacterMapper
import com.scallop.marveldex.ui.characterlist.CharacterListViewModel
import com.scallop.marveldex.ui.detail.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

@Suppress("USELESS_CAST") // It is important to maintain the dependency tree
val mRepositoryModules = module {
    single {
        RemoteDataSourceImpl(
            get()
        ) as RemoteDataSource
    }
    single {
        RepositoryImpl(
            get(),
            DataEntityMapper()
        ) as MarvelRepository
    }
}

val mUseCaseModules = module {
    factory { GetCharactersUseCase(get()) }
    factory { GetCharacterUseCase(get()) }
}

val mNetworkModules = module {
    single { createNetworkClient(BASE_URL, get()) }
    single { (get() as Retrofit).create(MarvelApi::class.java) }
}

val mViewModels = module {
    viewModel {
        CharacterListViewModel(
            get(GetCharactersUseCase::class) as GetCharactersBaseUseCase,
            CharacterMapper()
        )
    }
    viewModel { (id: Int) ->
        DetailViewModel(
            id,
            get(GetCharacterUseCase::class) as GetCharacterBaseUseCase,
            CharacterMapper()
        )
    }
}

private const val BASE_URL = "https://gateway.marvel.com"
