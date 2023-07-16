package com.example.suntask.ui.main.base

import android.app.Application
import com.example.suntask.data.dependency.ApplicationComponent
import com.example.suntask.data.dependency.DaggerApplicationComponent
import com.example.suntask.data.dependency.NetworkModule

class MyApplication : Application() {
    lateinit var component : ApplicationComponent
    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent.builder().networkModule(NetworkModule(this)).build()
    }
}