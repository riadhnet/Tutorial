package com.riadh.tutorial.time

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TimeServiceActivity : AppCompatActivity(), TimeServiceActionListener {

    private val viewModel: TimeServiceViewModel  by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setListener(this)
        setContent {
            TimeServiceLayout(viewModel)

        }
    }

    override fun startTimeService() {
        val intent = Intent(this, TimeService::class.java)
        startService(intent)

    }
    override fun stopTimeService() {
        val intent = Intent(this, TimeService::class.java)
        stopService(intent)
    }

}

