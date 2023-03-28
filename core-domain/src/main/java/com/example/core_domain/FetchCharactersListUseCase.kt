package com.example.core_domain

import com.example.core_data.CharactersListRepository
import javax.inject.Inject

class FetchCharacterListUseCase @Inject constructor(private val repository: CharactersListRepository) {
    operator fun invoke(page: Int) = repository.getCharactersList(page)
}
