package com.example.preparcialayd.injector

import android.content.Context
import com.example.preparcialayd.model.CriptoRepository
import com.example.preparcialayd.model.DataRepoImpl
import com.example.preparcialayd.model.external.CriptoRepositoryRemoteImpl
import com.example.preparcialayd.model.external.FreeCryptoAPI
import com.example.preparcialayd.model.local.CriptoRepositoryLocalImpl
import com.example.preparcialayd.presentation.CriptoPresenter
import com.example.preparcialayd.presentation.CriptoPresenterImpl

object CriptoInjector {
    lateinit var repository: CriptoRepository
    lateinit var presenter: CriptoPresenter

    fun init(context: Context) {
        val sharedPreferences = context.getSharedPreferences("MY_SHARED_PREFERENCES", Context.MODE_PRIVATE)
        val local = CriptoRepositoryLocalImpl(sharedPreferences)
        val external = CriptoRepositoryRemoteImpl(FreeCryptoAPI())
        repository = DataRepoImpl(local, external)
        presenter = CriptoPresenterImpl(repository)

    }
}