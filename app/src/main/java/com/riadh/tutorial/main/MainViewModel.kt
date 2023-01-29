package com.riadh.tutorial.main

import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.riadh.tutorial.TutorialApplication
import com.riadh.tutorial.time.TimeServiceActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val  application: TutorialApplication) : AndroidViewModel(application) {
    fun openTimeActivity() {
        val intent = Intent(application, TimeServiceActivity::class.java)
        application.startActivity(intent)
    }

    var status = MutableLiveData<String?>()

    val text = MutableLiveData<String>().apply {
        value = "Hello MVVM, Hilt, and Jetpack Compose!"
    }
}


