package com.example.preparcialayd.presentation

import ayds.observer.Observable
import ayds.observer.Subject
import com.example.preparcialayd.model.DataRepo
import kotlin.concurrent.thread
import kotlin.math.roundToInt

interface CriptoPresenter{
    val observer: Observable<Pair<String, Int>>

    fun fetchPrice(coin: String)
}

class CriptoPresenterImpl(private val repository: DataRepo) : CriptoPresenter {
    override val observer = Subject<Pair<String, Int>>()

    override fun fetchPrice(coin: String) {
        thread {
            val result = repository.fetchPrice(coin)
            observer.notify(Pair(coin, result.roundToInt()))
        }
    }
}