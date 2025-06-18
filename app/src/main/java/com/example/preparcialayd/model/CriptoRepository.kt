package com.example.preparcialayd.model

import com.example.preparcialayd.model.external.CriptoRepositoryRemote
import com.example.preparcialayd.model.local.CriptoRepositoryLocal

interface CriptoRepository{
    fun fetchPrice(coin: String): Double
}

class DataRepoImpl(
    private val localStorage: CriptoRepositoryLocal,
    private val remoteStorage: CriptoRepositoryRemote
) : CriptoRepository {

    override fun fetchPrice(coin: String): Double {
        val cachedPrice = localStorage.get(coin)
        return if (cachedPrice == -1.0) remoteStorage.get(coin) else cachedPrice
    }
}