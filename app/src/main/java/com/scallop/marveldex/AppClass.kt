package com.scallop.marveldex

import android.app.Application
import com.scallop.marveldex.di.mNetworkModules
import com.scallop.marveldex.di.mRepositoryModules
import com.scallop.marveldex.di.mUseCaseModules
import com.scallop.marveldex.di.mViewModels
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppClass : Application() {

    override fun onCreate() {
        super.onCreate()
        loadKoin()
    }

    private fun loadKoin() {
        startKoin {
            androidContext(this@AppClass)
            modules(
                listOf(
                    mNetworkModules,
                    mViewModels,
                    mRepositoryModules,
                    mUseCaseModules,
                )
            )
        }
    }
}
