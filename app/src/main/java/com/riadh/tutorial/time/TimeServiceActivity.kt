package com.riadh.tutorial.time

import android.Manifest
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@AndroidEntryPoint
class TimeServiceActivity : AppCompatActivity(), TimeServiceActionListener, TimeSpeechListener {

    private val viewModel: TimeServiceViewModel  by viewModels()
    private var timeService: TimeService? = null
    private var timeServiceBound = false

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName) {
            timeServiceBound = false
            timeService = null
        }

        override fun onServiceConnected(name: ComponentName, binder: IBinder) {
            val timeServiceBinder = binder as TimeService.TimeServiceBinder
            timeService = timeServiceBinder.getService()
            timeServiceBound = true
            timeService?.setListener(this@TimeServiceActivity)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setListener(this)
        setContent {
            TimeServiceLayout(viewModel)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (timeServiceBound) {
            timeService?.setListener(null)
            unbindService(serviceConnection)
            timeServiceBound = false
        }
    }

    override fun startTimeService() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WAKE_LOCK) == PackageManager.PERMISSION_GRANTED
            && ContextCompat.checkSelfPermission(this, Manifest.permission.VIBRATE) == PackageManager.PERMISSION_GRANTED) {
            val intent = Intent(this, TimeService::class.java)
            bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
            startService(intent)
        } else {
            requestPermissions()
        }
    }

    override fun stopTimeService() {
        val intent = Intent(this, TimeService::class.java)
        stopService(intent)
    }

    private fun requestPermissions() {
        val permissions = arrayOf(
            Manifest.permission.WAKE_LOCK,
            Manifest.permission.VIBRATE
        )
        ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE_PERMISSIONS)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_CODE_PERMISSIONS -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startTimeService()
                } else {
                    Toast.makeText(applicationContext, "no permission for shake", Toast.LENGTH_SHORT).show()
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    companion object {
        const val REQUEST_CODE_PERMISSIONS = 99991
    }

    override fun sayTime() {
        showCurrentTimeToast()
    }

    private fun showCurrentTimeToast() {
        val currentTime = Calendar.getInstance().time
        val formattedTime = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(currentTime)
        Toast.makeText(this, formattedTime, Toast.LENGTH_SHORT).show()
    }
}

