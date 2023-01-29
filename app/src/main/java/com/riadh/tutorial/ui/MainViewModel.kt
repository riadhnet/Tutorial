package com.riadh.tutorial.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    val text = MutableLiveData<String>().apply {
        value = "Hello MVVM, Hilt, and Jetpack Compose!"
    }
}