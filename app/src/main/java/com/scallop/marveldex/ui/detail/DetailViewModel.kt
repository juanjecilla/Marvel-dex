package com.scallop.marveldex.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scallop.marveldex.domain.entities.MarvelCharacterEntity
import com.scallop.marveldex.domain.entities.ResultWrapperEntity
import com.scallop.marveldex.domain.usecases.GetCharacterBaseUseCase
import com.scallop.marveldex.domain.usecases.GetCharacterUseCase
import com.scallop.marveldex.mappers.CharacterMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(
    id: Int,
    private val mUseCase: GetCharacterBaseUseCase,
    private val mMapper: CharacterMapper
) : ViewModel() {

    private val _data = MutableLiveData<DetailState>()
    val data: LiveData<DetailState> get() = _data

    init {
        if (id != -1) {
            getCharacter(id)
        }
    }

    private fun getCharacter(id: Int) {
        _data.value = DetailState.DetailLoading(true)
        viewModelScope.launch {
            val results = withContext(Dispatchers.IO) {
                mUseCase(GetCharacterUseCase.Params(id))
            }
            results.map {
                _data.value = DetailState.DetailLoading(false)
                _data.value =
                    DetailState.DetailSuccess(
                        mMapper.mapResult(
                            it as ResultWrapperEntity.Success<MarvelCharacterEntity>
                        ).value
                    )
            }.collect()
        }
    }
}
