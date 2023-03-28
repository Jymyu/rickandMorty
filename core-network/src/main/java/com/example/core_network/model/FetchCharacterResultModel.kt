package com.example.core_network.model

import com.example.core_model.CharacterModel

data class FetchCharacterResultModel(
    val id: Int? = 0,
    val name: String? = null,
    val image: String? = null,
    val status: String? = null,
    val species: String? = null,
    val type: String? = null,
)


fun FetchCharacterResultModel.asCharacterModel() = CharacterModel(
    id = id,
    name = name,
    imageUrl = image,
    species = species,
    status = status,
    type = type,
)