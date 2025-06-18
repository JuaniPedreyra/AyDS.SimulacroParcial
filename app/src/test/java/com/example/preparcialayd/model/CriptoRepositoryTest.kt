package com.example.preparcialayd.model

import com.example.preparcialayd.model.external.CriptoRepositoryRemote
import com.example.preparcialayd.model.local.CriptoRepositoryLocal
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert
import org.junit.Test

class CriptoRepositoryTest {
    private val localStorage: CriptoRepositoryLocal = mockk()
    private val externalRepository: CriptoRepositoryRemote = mockk()
    private val repository: CriptoRepository = DataRepoImpl(localStorage, externalRepository)

    @Test
    fun `on fetchPrice call get from localStorage`() {
        val symbol = "USD"
        val price = 1.0
        every { localStorage.get(symbol) } returns 1.0
        val result = repository.fetchPrice(symbol)
        Assert.assertEquals(price, result, 0.01)
        verify { localStorage.get(symbol) }
        verify(exactly = 0) { externalRepository.get(any()) }
    }


    @Test
    fun `on fetchPrice call get from externalRepo`() {
        val symbol = "EUR"
        val price = 1.0
        every { localStorage.get(symbol) } returns -1.0
        every { externalRepository.get(symbol) } returns 1.0
        val result = repository.fetchPrice(symbol)
        Assert.assertEquals(price, result, 0.01)
        verify { localStorage.get(symbol) }
        verify { externalRepository.get(symbol) }
    }

}