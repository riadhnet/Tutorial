package com.riadh.tutorial.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
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

@Preview
@Composable
fun MainLayoutPreview() {
    MainLayout(MainViewModel() , "1.4")
}
