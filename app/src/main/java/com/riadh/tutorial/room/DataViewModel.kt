package com.riadh.tutorial.room

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riadh.tutorial.room.model.MyEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DataViewModel @Inject constructor(val myDao: MyDao) : ViewModel() {

    val name = mutableStateOf("")
    val age = mutableStateOf("")

    val entities = MutableLiveData<List<MyEntity>>()

    //val myEntities = listOf(MyEntity(1, "John", 25), MyEntity(2, "Jane", 30))

    fun updateData() {
        viewModelScope.launch {
            val entitiesFromDb = getEntitiesFromDb()
            entities.postValue(entitiesFromDb)
        }
    }

    fun addEntity() {
        val parsedAge = age.value.toIntOrNull()
        if (parsedAge != null) {
            viewModelScope.launch(Dispatchers.IO) {
                myDao.insertEntity(MyEntity(0, name.value ?: "", parsedAge))
            }
            updateData()
        } else {
            // show error message for invalid age input
        }

        name.value = ""
        age.value = ""
    }

    private suspend fun getEntitiesFromDb(): List<MyEntity> = withContext(Dispatchers.IO) {
        getEntities()
    }
    private fun getEntities(): List<MyEntity> {
       return myDao.getAll()
    }

    fun clearDataBase() {
        viewModelScope.launch {
            myDao.deleteAllEntities()
        }
        updateData()
    }
}


