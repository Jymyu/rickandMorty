package com.example.core_data

import com.example.core_model.CharacterModel
import com.example.core_model.Resource
import kotlinx.coroutines.flow.Flow


interface CharactersListRepository {
    fun getCharactersList(page: Int): Flow<Resource<List<CharacterModel>>>
}