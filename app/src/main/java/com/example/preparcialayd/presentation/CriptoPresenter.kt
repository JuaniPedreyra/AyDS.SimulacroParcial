package com.example.preparcialayd.presentation

import ayds.observer.Observable
import ayds.observer.Subject
import com.example.preparcialayd.model.CriptoRepository
import kotlin.concurrent.thread
import kotlin.math.roundToInt

interface CriptoPresenter{
    val observer: Observable<Pair<String, Double>>

    fun fetchPrice(coin: String)
}

class CriptoPresenterImpl(private val repository: CriptoRepository) : CriptoPresenter {
    override val observer = Subject<Pair<String, Double>>()

    override fun fetchPrice(coin: String) {
        thread {
            val result = repository.fetchPrice(coin)
            observer.notify(Pair(coin, result))
        }
    }
}