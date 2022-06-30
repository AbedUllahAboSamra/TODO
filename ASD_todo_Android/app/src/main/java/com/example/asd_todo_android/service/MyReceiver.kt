package com.example.asd_todo_android.service

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.VIBRATOR_SERVICE
import android.content.Intent
import android.media.AudioAttributes
import android.os.Vibrator
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        val vibration = context.getSystemService(VIBRATOR_SERVICE) as Vibrator
        vibration.vibrate(5000)


    }
}