package com.nexcel.nexcellivescore

import android.app.Application
import timber.log.Timber

class NexcelApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}