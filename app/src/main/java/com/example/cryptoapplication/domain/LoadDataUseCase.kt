package com.example.cryptoapplication.domain

class LoadDataUseCase(private val coinRepository: CoinRepository) {
   operator fun invoke() = coinRepository.loadData()
}