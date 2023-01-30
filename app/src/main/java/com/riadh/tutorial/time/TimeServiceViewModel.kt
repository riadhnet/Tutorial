package com.riadh.tutorial.time

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TimeServiceViewModel @Inject constructor() : ViewModel() {

    private var listener: TimeServiceActionListener? = null

    val time = MutableLiveData("")
    var enabled = MutableLiveData<Boolean?>()

    fun startTimeService() {
        listener?.startTimeService()
    }

    fun stopTimeService() {
        listener?.stopTimeService()
    }

    fun setListener(listener: TimeServiceActivity) {
        this.listener = listener
    }
}
