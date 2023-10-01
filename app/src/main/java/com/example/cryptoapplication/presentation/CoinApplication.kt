package com.example.cryptoapplication.presentation

import android.app.Application
import com.example.cryptoapplication.di.DaggerApplicationComponent

class CoinApplication: Application()  {
    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}