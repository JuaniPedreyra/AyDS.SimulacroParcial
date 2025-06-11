package com.example.preparcialayd.model

import C.FreeCryptoAPI
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.preparcialayd.injector.CriptoInjector

interface DataRepo{
    fun fetchPrice(coin: String): Double
}

interface CriptoRemote{
    fun getData(): Double
}

interface CriptoLocal{
    fun getData(): Double
}


class DataRepoImpl(
    private val sharedPreferences: SharedPreferences,
    private val localStorage: CriptoLocal,
    private val remoteStorage: CriptoRemote
) : DataRepo {

    override fun fetchPrice(coin: String): Double {
        val cachedCoin = sharedPreferences.getString(coin, null)
        var result : Double

        if(cachedCoin == null) {
            result = FreeCryptoAPI().get(coin)
            sharedPreferences.edit { putString(coin, result.toString()) }
        } else {
            result = try {
                cachedCoin.toDouble()
            } catch (e: Exception) {
                0.0
            }
        }
        return result
    }
}