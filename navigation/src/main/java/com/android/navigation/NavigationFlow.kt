package com.android.navigation

import com.example.core_model.CharacterModel

sealed class NavigationFlow {

    class RickAndMortyListToCharacterDetailsFlow(val characterModel: CharacterModel) :
        NavigationFlow()

}
