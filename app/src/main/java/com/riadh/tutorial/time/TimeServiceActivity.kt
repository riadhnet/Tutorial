package com.riadh.tutorial.time

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TimeServiceActivity : AppCompatActivity(), TimeServiceActionListener {

    private val viewModel: TimeServiceViewModel  by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setListener(this)
        setContent {
            TimeServiceLayout(viewModel)
        }
       // requestPermissions()
    }

    override fun startTimeService() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WAKE_LOCK) == PackageManager.PERMISSION_GRANTED
            && ContextCompat.checkSelfPermission(this, Manifest.permission.VIBRATE) == PackageManager.PERMISSION_GRANTED) {
            val intent = Intent(this, TimeService::class.java)
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

}

