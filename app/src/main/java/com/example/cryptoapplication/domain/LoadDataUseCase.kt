package com.example.cryptoapplication.domain

class LoadDataUseCase(private val coinRepository: CoinRepository) {
   suspend operator fun invoke() = coinRepository.loadData()
}