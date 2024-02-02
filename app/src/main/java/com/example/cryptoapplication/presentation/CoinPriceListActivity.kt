package com.example.cryptoapplication.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapplication.R
import com.example.cryptoapplication.databinding.ActivityCoinPriceListBinding
import com.example.cryptoapplication.domain.CoinInfo
import com.example.cryptoapplication.presentation.adapters.CoinInfoAdapter
import javax.inject.Inject

class CoinPriceListActivity : AppCompatActivity() {
    private val binding: ActivityCoinPriceListBinding by lazy {
        ActivityCoinPriceListBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: CoinViewModel

    private val component by lazy {
        (application as CoinApplication).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val adapter = CoinInfoAdapter(this)
        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinPriceInfo: CoinInfo) {
                if (isOnePaneMode()) {
                    launchDetailActivity(coinPriceInfo.fromSymbol)
                } else {
                    launchDetailFragment(coinPriceInfo.fromSymbol)
                }
            }
        }

        binding.rvCoinPriceList.adapter = adapter

        viewModel = ViewModelProvider(this, viewModelFactory)[CoinViewModel::class.java]
        viewModel.coinInfoList.observe(this) {
            adapter.submitList(it)
        }
    }

    private fun isOnePaneMode() = binding.fragmentContainer == null

    private fun launchDetailActivity(fromSymbol: String) {
        val intent = CoinDetailActivity.newIntent(
            this@CoinPriceListActivity,
            fromSymbol
        )

        startActivity(intent)
    }

    private fun launchDetailFragment(fromSymbol: String) {
        supportFragmentManager.popBackStack()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, CoinDetailFragment.newInstance(fromSymbol))
            .addToBackStack(null)
            .commit()
    }
}
