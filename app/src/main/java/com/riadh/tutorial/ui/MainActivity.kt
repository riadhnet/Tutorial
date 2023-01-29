package com.riadh.tutorial.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.riadh.tutorial.util.getAppVersion
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainLayout(viewModel, getAppVersion())
        }

        viewModel.status.observe(this) { status ->
            status?.let {
                //Reset status value at first to prevent multiTriggering
                //and to be available to trigger action again
                viewModel.status.value = null
                Toast.makeText(
                    this,
                    status,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }



}

