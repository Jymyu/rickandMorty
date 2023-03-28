package com.example.core_network.characterInfo

import com.example.core_model.Resource
import com.example.core_network.api.NetworkApi
import com.example.core_network.model.FetchCharacterResponseModel
import com.example.core_network.requestData
import javax.inject.Inject

class CharactersInfoDataSourceImp @Inject constructor(
    private val networkApi: NetworkApi
) : CharactersInfoDataSource {

    override suspend fun fetchCharactersInfo(page: Int): Resource<FetchCharacterResponseModel> =
        requestData {
            networkApi.fetchCharacters(page)
        }

}