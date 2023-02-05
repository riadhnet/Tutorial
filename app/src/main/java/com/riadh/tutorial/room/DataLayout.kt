package com.riadh.tutorial.room

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import com.riadh.tutorial.room.model.MyEntity

@Composable
fun DataLayout(viewModel: DataViewModel) {
    MaterialTheme {
        Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                AddEntity(viewModel)
                Spacer(modifier = Modifier.height(16.dp))
                MyEntityList(viewModel.entities)
            }

        }
    }
}

@Composable
fun MyEntityList(myEntitiesLiveData: LiveData<List<MyEntity>>) {
    val myEntities by myEntitiesLiveData.observeAsState()
    Column {
        myEntities?.let {
            for (myEntity in it) {
                Text("ID: ${myEntity.id}")
                Text("Name: ${myEntity.name}")
                Text("Age: ${myEntity.age}")
            }
        }
    }
}

@Composable
fun AddEntity(viewModel: DataViewModel) {

    Column {
        Button(onClick = {
            viewModel.clearDataBase()

        }) {
            Text("clear data base")
        }
        TextField(
            label = { Text("Age = ") },
            value = viewModel.age.value ?: "",
            onValueChange = { viewModel.age.value = it }
        )
        TextField(
            label = { Text("Name = ") },
            value = viewModel.name.value ?: "",
            onValueChange = { viewModel.name.value = it },
        )
        Button(onClick = {
            viewModel.addEntity()

        }) {
            Text("Save")
        }
    }
}




