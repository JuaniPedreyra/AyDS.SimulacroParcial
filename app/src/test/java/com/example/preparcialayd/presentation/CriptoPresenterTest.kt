package com.example.preparcialayd.presentation

import ayds.observer.Observer
import com.example.preparcialayd.model.CriptoRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test


class CriptoPresenterTest {
    private val repository : CriptoRepository = mockk()
    private val presenter = CriptoPresenterImpl(repository)

    @Test
    fun `get price should return a double with the price of the coin`() {
        val symbol = "USD"
        val price = 1.0
        every {repository.fetchPrice(symbol) } returns 1.0

        val observerMock = mockk<Observer<Pair<String,Double>>>(relaxed = true)
        presenter.observer.subscribe(observerMock)

        presenter.fetchPrice(symbol)

        verify { observerMock.update(Pair(symbol, price)) }
    }




}