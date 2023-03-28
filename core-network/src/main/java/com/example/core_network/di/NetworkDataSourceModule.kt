package com.example.core_network.di

import com.example.core_network.characterInfo.CharactersInfoDataSourceImp
import com.example.core_network.characterInfo.CharactersInfoDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class NetworkDataSourceModule {

    @Binds
    abstract fun bindCharacterInfoDataSource(characterInfoDataSource: CharactersInfoDataSourceImp): CharactersInfoDataSource
}