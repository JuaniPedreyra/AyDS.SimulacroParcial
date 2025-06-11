package com.example.preparcialayd.injector

import android.content.Context
import android.content.SharedPreferences
import com.example.preparcialayd.model.DataRepo
import com.example.preparcialayd.model.DataRepoImpl
import com.example.preparcialayd.presentation.CriptoPresenter
import com.example.preparcialayd.presentation.CriptoPresenterImpl

object CriptoInjector {
    lateinit var repository: DataRepo
    lateinit var presenter: CriptoPresenter
    lateinit var sharedPreferences : SharedPreferences

    fun init(context: Context) {
        val sharedPreferences = context.getSharedPreferences("MY_SHARED_PREFERENCES", Context.MODE_PRIVATE)
        repository = DataRepoImpl(sharedPreferences, local, external)
        presenter = CriptoPresenterImpl(repository)
    }
}