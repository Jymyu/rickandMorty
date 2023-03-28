package com.example.core_data

import com.example.core_model.CharacterModel
import com.example.core_model.Resource
import com.example.core_network.characterInfo.CharactersInfoDataSource
import com.example.core_network.model.asCharacterModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharactersListRepositoryImp @Inject constructor(private val charactersInfoDataSource: CharactersInfoDataSource) :
    CharactersListRepository {

    override fun getCharactersList(page: Int): Flow<Resource<List<CharacterModel>>> = flow {
        emit(Resource.Loading())
        val remoteResponse = charactersInfoDataSource.fetchCharactersInfo(page)
        if (remoteResponse is Resource.Success) {
            remoteResponse.data?.results?.map { characterItem -> characterItem.asCharacterModel() }
                ?.let { charactersList ->
                    emit(Resource.Success(charactersList))
                }
        } else
            emit(Resource.Error("There's no data"))
    }
}