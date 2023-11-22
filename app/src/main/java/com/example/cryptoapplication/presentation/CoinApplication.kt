package com.example.cryptoapplication.presentation

import android.app.Application
import androidx.work.Configuration
import com.example.cryptoapplication.data.database.AppDatabase
import com.example.cryptoapplication.data.mapper.CoinMapper
import com.example.cryptoapplication.data.network.ApiFactory
import com.example.cryptoapplication.data.workers.RefreshDataWorkerFactory
import com.example.cryptoapplication.di.DaggerApplicationComponent

class CoinApplication: Application(), Configuration.Provider {
    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(
                RefreshDataWorkerFactory(
                    AppDatabase.getInstance(this).coinPriceInfoDao(),
                    ApiFactory.apiService,
                    CoinMapper()
                )
            )
            .build()
    }
}