package com.example.preparcialayd.model.external

interface CriptoRepositoryRemote{
    fun get(symbol: String): Double
}


class CriptoRepositoryRemoteImpl(
    private val api: FreeCryptoAPI
): CriptoRepositoryRemote {

    override fun get(symbol: String): Double {
        return api.get(symbol)
    }
}