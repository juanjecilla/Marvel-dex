package com.scallop.marveldex.ui.characterlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scallop.marveldex.domain.usecases.GetCharactersBaseUseCase
import com.scallop.marveldex.domain.usecases.GetCharactersUseCase
import com.scallop.marveldex.entities.MarvelCharacter
import com.scallop.marveldex.mappers.CharacterMapper
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
                mUseCase(GetCharactersUseCase.Params(20, page * 20))
            }
            results.map {
                items.addAll(mMapper.mapCharacters(it))

                _data.value = CharacterListState.CharacterListLoading(false)
                _data.value = CharacterListState.CharacterListItems(items)
            }.collect()
        }
    }
}