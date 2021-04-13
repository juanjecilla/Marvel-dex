package com.scallop.marveldex.ui.characterlist

import com.scallop.marveldex.entities.MarvelCharacter


sealed class CharacterListState {
    data class CharacterListItems(val items: List<MarvelCharacter>) : CharacterListState()
    data class CharacterListFailure(val failure: String) : CharacterListState()
    data class CharacterListLoading(val show: Boolean) : CharacterListState()
}