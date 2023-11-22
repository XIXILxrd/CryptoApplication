package com.example.cryptoapplication.presentation

import android.app.Application
import androidx.work.Configuration
import com.example.cryptoapplication.data.workers.RefreshDataWorkerFactory
import com.example.cryptoapplication.di.DaggerApplicationComponent
import javax.inject.Inject

class CoinApplication: Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: RefreshDataWorkerFactory

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
    }
}