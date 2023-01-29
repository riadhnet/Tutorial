package com.riadh.tutorial.time

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class TimeServiceActivity : AppCompatActivity() {

    private val viewModel: TimeServiceViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TimeServiceLayout(viewModel)
        }
    }
}