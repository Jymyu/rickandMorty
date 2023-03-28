package com.example.core_network.api

import com.example.core_network.NetworkConstants.GET_CHARACTER
import com.example.core_network.model.FetchCharacterResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkApi {

    @GET(GET_CHARACTER)
    suspend fun fetchCharacters(@Query("page") pageNumber: Int): FetchCharacterResponseModel
}