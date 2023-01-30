package com.riadh.tutorial.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MainLayout(viewModel: MainViewModel, appVersion: String) {
    MaterialTheme {
        Surface(color = Color.White) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                viewModel.text.value?.let {
                    Text(
                        text = it,
                        modifier = Modifier.wrapContentSize()
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        viewModel.openTimeActivity()
                    },
                    modifier = Modifier.wrapContentSize()
                ){
                    Text("Shake Timer App")
                }

                Button(
                    onClick = {
                        viewModel.status.value = "app2"
                    },
                    modifier = Modifier.wrapContentSize()
                ){
                    Text("App2")
                }

                Button(
                    onClick = {
                        viewModel.status.value = "app3"
                    },
                    modifier = Modifier.wrapContentSize()
                ){
                    Text("App3")
                }

                Spacer(modifier = Modifier.weight(1f))
                val textStyle = MaterialTheme.typography.body2
                Text(
                    text = "Version: $appVersion",
                    style = textStyle,
                    modifier = Modifier.wrapContentSize()
                )
            }
        }
    }
}




