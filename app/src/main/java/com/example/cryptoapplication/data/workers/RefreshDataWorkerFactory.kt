package com.example.cryptoapplication.data.workers

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.example.cryptoapplication.data.database.CoinInfoDao
import com.example.cryptoapplication.data.mapper.CoinMapper
import com.example.cryptoapplication.data.network.ApiService

class RefreshDataWorkerFactory(
    private val coinInfoDao: CoinInfoDao,
    private val apiService: ApiService,
    private val mapper: CoinMapper
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
       return RefreshDataWorker(appContext, workerParameters, coinInfoDao, apiService, mapper)
    }
}