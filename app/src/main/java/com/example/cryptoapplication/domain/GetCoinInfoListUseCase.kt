package com.example.cryptoapplication.domain

class GetCoinInfoListUseCase(private val coinRepository: CoinRepository) {
    operator fun invoke() = coinRepository.getCoinInfoList()
}