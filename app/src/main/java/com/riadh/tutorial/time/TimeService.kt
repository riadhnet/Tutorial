package com.riadh.tutorial.time

import android.app.Service
import android.content.Intent
import android.os.IBinder

class TimeService : Service() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Perform your background task here

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}
