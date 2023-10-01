package com.example.cryptoapplication.presentation

import androidx.lifecycle.ViewModel
import com.example.cryptoapplication.domain.GetCoinInfoListUseCase
import com.example.cryptoapplication.domain.GetCoinInfoUseCase
import com.example.cryptoapplication.domain.LoadDataUseCase
import javax.inject.Inject

class CoinViewModel @Inject constructor(
    private val getCoinInfoUseCase: GetCoinInfoUseCase,
    private val getCoinInfoListUseCase: GetCoinInfoListUseCase,
    private val loadDataUseCase: LoadDataUseCase
) : ViewModel() {


    val coinInfoList = getCoinInfoListUseCase()

    fun getDetailInfo(fSym: String) = getCoinInfoUseCase(fSym)

    init {
        loadDataUseCase()
    }
}