package com.example.cryptoapplication.data.mapper

import com.example.cryptoapplication.data.database.CoinInfoDatabaseModel
import com.example.cryptoapplication.data.network.model.CoinInfoDTO
import com.example.cryptoapplication.data.network.model.CoinInfoJsonContainerDTO
import com.example.cryptoapplication.data.network.model.CoinNamesListDTO
import com.example.cryptoapplication.domain.CoinInfo
import com.google.gson.Gson

class CoinMapper {
    fun mapDtoToDbModel(dto: CoinInfoDTO): CoinInfoDatabaseModel = CoinInfoDatabaseModel(
        fromSymbol = dto.fromSymbol,
        toSymbol = dto.toSymbol,
        price = dto.price,
        lastUpdate = dto.lastUpdate,
        highDay = dto.highDay,
        lowDay = dto.lowDay,
        lastMarket = dto.lastMarket,
        imageUrl = dto.imageUrl
    )

    fun mapJsonContainerToListContainer(jsonContainer: CoinInfoJsonContainerDTO): List<CoinInfoDTO> {
        val result = mutableListOf<CoinInfoDTO>()
        val jsonObject = jsonContainer.json ?: return result
        val coinKeySet = jsonObject.keySet()

        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinInfoDTO::class.java
                )

                result.add(priceInfo)
            }
        }

        return result
    }

    fun mapNamesListToString(namesList: CoinNamesListDTO): String {
        return namesList.names?.map {
            it.coinNameDTO?.name
        }?.joinToString(",") ?: ""
    }

    fun mapDbModelToEntity(dbModel: CoinInfoDatabaseModel) = CoinInfo(
        fromSymbol = dbModel.fromSymbol,
        toSymbol = dbModel.toSymbol,
        price = dbModel.price,
        lastUpdate = dbModel.lastUpdate,
        highDay = dbModel.highDay,
        lowDay = dbModel.lowDay,
        lastMarket = dbModel.lastMarket,
        imageUrl = dbModel.imageUrl
    )
}