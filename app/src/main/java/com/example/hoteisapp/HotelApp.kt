package com.example.hoteisapp

import android.app.Application
import com.example.hoteisapp.di.androidModule
import org.koin.android.ext.android.startKoin
import org.koin.standalone.StandAloneContext.stopKoin

class HotelApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(androidModule))
    }
    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }
}