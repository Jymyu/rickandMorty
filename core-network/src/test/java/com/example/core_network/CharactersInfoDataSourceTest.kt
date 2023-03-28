package com.example.core_network

import com.example.core_network.api.NetworkApi
import com.example.core_network.characterInfo.CharactersInfoDataSourceImp
import io.mockk.clearAllMocks
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@OptIn(ExperimentalCoroutinesApi::class)
class CharactersInfoDataSourceTest {
    private lateinit var dataSourceImpl: CharactersInfoDataSourceImp

    private val networkApi = mockk<NetworkApi>(relaxed = true)

    @Before
    fun setup() {
        dataSourceImpl = CharactersInfoDataSourceImp((networkApi))
    }

    @Test
    fun `datasource fetchCharactersInfo should call apiInterface fetchCharacters`() = runTest {
        val page = 1
        dataSourceImpl.fetchCharactersInfo(page)
        coVerify { networkApi.fetchCharacters(page) }
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }
}