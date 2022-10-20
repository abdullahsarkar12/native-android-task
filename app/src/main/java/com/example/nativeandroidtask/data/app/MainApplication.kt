package com.example.nativeandroidtask.data.app

import android.app.Application

class MainApplication : Application() {

    companion object {
        private lateinit var mInstance: MainApplication

        fun getInstance() : MainApplication {
            return mInstance
        }
    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this

    }

}