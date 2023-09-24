package com.example.cryptoapplication.data.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.cryptoapplication.data.database.AppDatabase
import com.example.cryptoapplication.data.mapper.CoinMapper
import com.example.cryptoapplication.data.network.ApiFactory
import com.example.cryptoapplication.domain.CoinInfo
import com.example.cryptoapplication.domain.CoinRepository
import kotlinx.coroutines.delay

class CoinRepositoryImplementation(application: Application) : CoinRepository {
    private val coinInfoDao = AppDatabase.getInstance(application).coinPriceInfoDao()
    private val mapper = CoinMapper()
    private val apiService = ApiFactory.apiService

    override fun getCoinInfoList(): LiveData<List<CoinInfo>> {
        return coinInfoDao.getPriceList().map {
            it.map { dbModel ->
                mapper.mapDbModelToEntity(dbModel)
            }
        }
    }

    override fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo> {
        return coinInfoDao.getPriceInfoAboutCoin(fromSymbol).map {
            mapper.mapDbModelToEntity(it)
        }
    }

    override suspend fun loadData() {
        while (true) {
            try {
                val topCoins = apiService.getTopCoinsInfo(limit = 50)
                val fromSymbols = mapper.mapNamesListToString(topCoins)
                val jsonContainer = apiService.getFullPriceList(fSyms = fromSymbols)
                val coinInfoDtoList = mapper.mapJsonContainerToListContainer(jsonContainer)
                val dbModelList = coinInfoDtoList.map {
                    mapper.mapDtoToDbModel(it)
                }

                coinInfoDao.insertPriceList(dbModelList)
            } catch (e: Exception) {
                Log.i("LoadData", "error")
            }

            delay(10000)
        }
    }
}