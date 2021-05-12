package com.scallop.marveldex.ui.characterlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scallop.marveldex.domain.entities.MarvelCharacterEntity
import com.scallop.marveldex.domain.entities.ResultWrapperEntity
import com.scallop.marveldex.domain.usecases.GetCharactersBaseUseCase
import com.scallop.marveldex.domain.usecases.GetCharactersUseCase
import com.scallop.marveldex.entities.MarvelCharacter
import com.scallop.marveldex.mappers.CharacterMapper
import com.scallop.marveldex.ui.commons.Properties
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterListViewModel(
    private val mUseCase: GetCharactersBaseUseCase,
    private val mMapper: CharacterMapper,
    private val mDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _data = MutableLiveData<CharacterListState>()
    val data: LiveData<CharacterListState> get() = _data

    private val items = mutableListOf<MarvelCharacter>()

    init {
        getCharacters(0)
    }

    fun getCharacters(page: Int) {
        _data.value = CharacterListState.CharacterListLoading(true)
        viewModelScope.launch {
            val results = withContext(mDispatcher) {
                mUseCase(GetCharactersUseCase.Params(Properties.ITEMS_PER_PAGE, page * Properties.ITEMS_PER_PAGE))
            }
            results.map {
                _data.value = CharacterListState.CharacterListLoading(false)

                when (it) {
                    is ResultWrapperEntity.Success<*> -> {
                        items.addAll(mMapper.mapResults(
                            it as ResultWrapperEntity.Success<List<MarvelCharacterEntity>>)
                            .value)
                        _data.value = CharacterListState.CharacterListItems(items)
                    }

                    is ResultWrapperEntity.GenericError -> {
                        _data.value = CharacterListState.CharacterListFailure(
                            it.exception.toString()
                        )
                    }
                    else -> throw IllegalStateException(it.toString())
                }
            }.collect()
        }
    }
}
