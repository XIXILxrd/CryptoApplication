package com.example.cryptoapplication.di

import android.app.Application
import com.example.cryptoapplication.presentation.CoinApplication
import com.example.cryptoapplication.presentation.CoinDetailFragment
import com.example.cryptoapplication.presentation.CoinPriceListActivity
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class,
        WorkerModule::class
    ]
)
interface ApplicationComponent {

    fun inject(activity: CoinPriceListActivity)

    fun inject(fragment: CoinDetailFragment)

    fun inject(application: CoinApplication)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}