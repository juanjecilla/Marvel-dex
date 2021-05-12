package com.scallop.marveldex.ui.detail

import com.scallop.marveldex.entities.MarvelCharacter


sealed class DetailState {
    data class DetailSuccess(val item: MarvelCharacter) : DetailState()
    data class DetailFailure(val failure: String) : DetailState()
    data class DetailLoading(val show: Boolean) : DetailState()
}
