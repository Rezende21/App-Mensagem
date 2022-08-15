package com.example.lesttalk.application

import android.app.Application
import java.lang.IllegalStateException

class TalkApplication : Application() {

    companion object {
        private var instance : TalkApplication? = null

        fun getInstance() =
            instance ?: throw IllegalStateException("Application not configured")
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}