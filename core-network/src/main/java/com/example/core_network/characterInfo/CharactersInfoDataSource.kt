package com.example.core_network.characterInfo

import com.example.core_model.Resource
import com.example.core_network.model.FetchCharacterResponseModel

interface CharactersInfoDataSource {

    suspend fun fetchCharactersInfo(page: Int): Resource<FetchCharacterResponseModel>
}