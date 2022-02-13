package com.example.bingocard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import com.example.bingocard.databinding.ActivityMainBinding

/**
 * ビンゴカード画面_Activity
 */

class MainActivity : AppCompatActivity() {

    private val mMainViewModel = MainViewModel()


    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.inflate<ActivityMainBinding>(
            layoutInflater,
            R.layout.activity_main,
            null,
            false
        ).also {
            it.lifecycleOwner = this
            it.viewModel = mMainViewModel
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        mMainViewModel.createBingoCard()
    }
}