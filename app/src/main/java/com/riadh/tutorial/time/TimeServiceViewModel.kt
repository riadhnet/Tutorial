package com.riadh.tutorial.time

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TimeServiceViewModel @Inject constructor() : ViewModel() {
    val time = MutableLiveData("")

    var enabled = MutableLiveData<Boolean?>()

}
