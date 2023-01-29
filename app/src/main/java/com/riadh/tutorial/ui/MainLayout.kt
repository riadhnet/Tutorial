package com.riadh.tutorial.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MainLayout(viewModel: MainViewModel) {
    MaterialTheme {
        Surface(color = Color.White) {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)) {
                viewModel.text.value?.let {
                    Text(
                        text = it,
                        modifier = Modifier.wrapContentSize()
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun MainLayoutPreview() {
    MainLayout(MainViewModel())
}
