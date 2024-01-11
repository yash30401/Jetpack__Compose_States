package com.yash.jetpackcomposestates.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class JetpackComposeApplication:Application() {
    override fun onCreate() {
        super.onCreate()
    }
}