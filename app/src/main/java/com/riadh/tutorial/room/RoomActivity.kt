package com.riadh.tutorial.room

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoomActivity : AppCompatActivity() {

    private val viewModel: DataViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.updateData()

        setContent {
            DataLayout(viewModel)
        }
    }
}

