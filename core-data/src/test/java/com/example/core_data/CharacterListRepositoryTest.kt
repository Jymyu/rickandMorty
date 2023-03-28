package com.example.core_data

import com.example.core_model.Resource
import com.example.core_network.characterInfo.CharactersInfoDataSource
import com.example.core_network.model.FetchCharacterResponseModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class CharacterListRepositoryTest {
    private lateinit var charactersListRepositoryImpl: CharactersListRepositoryImp

    private val charactersListDataSource = mockk<CharactersInfoDataSource>(relaxed = true)

    @Before
    fun setup() {
        charactersListRepositoryImpl = CharactersListRepositoryImp(charactersListDataSource)
    }

    @Test
    fun `when repository getCharactersList is called and result is error `() = runTest {
        val page = 1
        coEvery { charactersListDataSource.fetchCharactersInfo(page) } returns Resource.Error("Error")

        charactersListRepositoryImpl.getCharactersList(page).collect {}

        coVerify { charactersListDataSource.fetchCharactersInfo(page) }
    }

    @Test
    fun `when repository getCharactersList is called and result is success `() = runTest {
        val page = 1
        coEvery { charactersListDataSource.fetchCharactersInfo(page) } returns Resource.Success(
            FetchCharacterResponseModel()
        )

        charactersListRepositoryImpl.getCharactersList(page).collect {}

        coVerify { charactersListDataSource.fetchCharactersInfo(page) }
    }

}