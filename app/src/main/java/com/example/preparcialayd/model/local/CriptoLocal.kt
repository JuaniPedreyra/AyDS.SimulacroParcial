package com.example.preparcialayd.model.local

import android.content.SharedPreferences
import com.example.preparcialayd.model.local.CachedCoinHelper.stringToDouble

interface CriptoRepositoryLocal{
    fun get(symbol: String): Double
}

class CriptoRepositoryLocalImpl(
    private val sharedPreferences: SharedPreferences
): CriptoRepositoryLocal{

    override fun get(symbol: String): Double {
        val cachedCoin = sharedPreferences.getString(symbol, null)
        return if (cachedCoin == null) -1.0 else stringToDouble(cachedCoin)
    }
}


object CachedCoinHelper{
    fun stringToDouble(cachedCoin: String?): Double{
        return try {
            cachedCoin!!.toDouble()
        } catch (e: Exception) {
            0.0
        }
    }
}