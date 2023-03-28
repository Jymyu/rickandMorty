package com.example.core_data.di

import com.example.core_data.CharactersListRepositoryImp
import com.example.core_data.CharactersListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindCharactersListRepository(charactersListRepository: CharactersListRepositoryImp): CharactersListRepository
}