package com.riadh.tutorial.time

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TimeServiceLayout(viewModel: TimeServiceViewModel) {
    val enabled = viewModel.enabled.observeAsState(false)
    MaterialTheme {
        Surface(color = Color.White) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Enable service to read text on phone shake",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.wrapContentSize()
                )

                Spacer(modifier = Modifier.height(16.dp))
                Switch(
                    onCheckedChange = { isChecked ->
                        viewModel.enabled.value = isChecked
                        if (isChecked) {
                            viewModel.startTimeService()
                        } else {
                            viewModel.stopTimeService()
                        }
                    },
                    checked = enabled.value ?: false,

                    )

                Text(
                    text = "Time Reader Enabled",
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.wrapContentSize()
                )
            }
        }
    }
}

@Preview
@Composable
fun TimeServiceLayoutPreview() {
    TimeServiceLayout(TimeServiceViewModel())
}
