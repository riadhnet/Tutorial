package com.riadh.tutorial.time

import android.app.Service
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Binder
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log

class TimeService : Service(), SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private var accelerometer: Sensor? = null

    private var timeSpeechListener: TimeSpeechListener? = null

    var alreadyCalled = false

    class TimeServiceBinder(private val service: TimeService) : Binder() {
        fun getService(): TimeService {
            return service
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i("TimeService", "TimeService started")
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
        return super.onStartCommand(intent, flags, startId)
    }

    fun setListener(listener: TimeSpeechListener?) {
        timeSpeechListener = listener
    }

    override fun onBind(intent: Intent): IBinder {
        return TimeServiceBinder(this)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Not required for this implementation
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event != null) {
            if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
                val x = event.values[0]
                val y = event.values[1]
                val z = event.values[2]
                val acceleration = (x * x + y * y + z * z) / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH)

                if (acceleration > 2 && !alreadyCalled) {
                    alreadyCalled = true
                    Log.i("TimeService", "say time")
                    timeSpeechListener?.sayTime()
                    Handler(Looper.getMainLooper()).postDelayed({
                        alreadyCalled = false
                    }, 10000)
                }
            }
        }
    }

    override fun onDestroy() {
        Log.i("TimeService", "TimeService destroyed")
        super.onDestroy()
        sensorManager.unregisterListener(this)
    }
}
