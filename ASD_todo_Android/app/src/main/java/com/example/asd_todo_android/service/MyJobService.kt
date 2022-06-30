package com.example.asd_todo_android.service

import android.app.*
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import android.content.Context
import android.os.IBinder
import android.util.Log
import androidx.core.app.JobIntentService
import androidx.core.content.ContextCompat.getSystemService
import com.example.asd_todo_android.screens.SplachActivity
import java.util.*

class MyJobService : IntentService("asdasd") {
    var alarmManager: AlarmManager? = null


    override fun onDestroy() {

        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager

        for (i in SplachActivity.allTasks) {
                var calendar = Calendar.getInstance()

                // calendar is called to get current time in hour and minute
                calendar.set(Calendar.HOUR_OF_DAY, calendar.time.hours)
                calendar.set(Calendar.MINUTE, calendar.time.minutes)

                // using intent i have class AlarmReceiver class which inherits
                // BroadcastReceiver
                var intent = Intent(this, MyReceiver::class.java)

                // we call broadcast using pendingIntent
                var pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)

                alarmManager!!.setAlarmClock(
                    AlarmManager.AlarmClockInfo(calendar.timeInMillis, pendingIntent),
                    pendingIntent

                )
                // alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (time * 1000), pendingIntent);

            }

        return START_NOT_STICKY
    }

    override fun onHandleIntent(intent: Intent?) {

    }

}