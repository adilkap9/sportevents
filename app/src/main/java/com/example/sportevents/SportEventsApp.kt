package com.example.sportevents

import android.app.Application
import com.example.sportevents.data.Constants.Companion.ONESIGNAL_APP_ID
import com.onesignal.OneSignal

class SportEventsApp: Application() {

    override fun onCreate() {
        super.onCreate()

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)

        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }
}