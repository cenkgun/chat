package com.cenkgun.chat

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import net.danlew.android.joda.JodaTimeAndroid

@HiltAndroidApp
class ChatApp : Application() {
    override fun onCreate() {
        super.onCreate()
        JodaTimeAndroid.init(this)
    }
}