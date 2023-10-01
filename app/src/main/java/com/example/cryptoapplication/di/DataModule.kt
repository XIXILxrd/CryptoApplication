package com.example.cryptoapplication.di

import android.app.Application
import com.example.cryptoapplication.data.database.AppDatabase
import com.example.cryptoapplication.data.database.CoinInfoDao
import com.example.cryptoapplication.data.repository.CoinRepositoryImplementation
import com.example.cryptoapplication.domain.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    fun bindCoinRepository(impl: CoinRepositoryImplementation): CoinRepository

    companion object {
        @Provides
        fun provideCoinInfoDao(
            application: Application
        ): CoinInfoDao {
            return AppDatabase.getInstance(application).coinPriceInfoDao()
        }
    }
}