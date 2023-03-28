package com.example.core_domain

import com.example.core_data.CharactersListRepository
import io.mockk.clearAllMocks
import io.mockk.coVerify
import io.mockk.mockk
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class FetchCharacterListUseCaseTest {
    private lateinit var fetchCharacterListUseCase: FetchCharacterListUseCase

    private val characterListRepository = mockk<CharactersListRepository>(relaxed = true)

    @Before
    fun setup() {
        fetchCharacterListUseCase = FetchCharacterListUseCase(characterListRepository)
    }

    @Test
    fun `invoke should call repository and get data by page`() {
        val page = 1
        fetchCharacterListUseCase.invoke(page)
        coVerify { characterListRepository.getCharactersList(page) }
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }
}