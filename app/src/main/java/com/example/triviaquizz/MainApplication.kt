package com.example.triviaquizz

import android.app.Application
import android.content.Context

class MainApplication: Application() {
    companion object {
        private var app: Application? = null
        fun getApplication(): Application? {
            return app
        }

        fun getContext(): Context? {
            return getApplication()!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        MainApplication.Companion.app = this
    }
}