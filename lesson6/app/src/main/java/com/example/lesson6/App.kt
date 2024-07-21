package com.example.lesson6

import android.app.Application
import com.example.lesson6.di.ApplicationComponent
import com.example.lesson6.di.DaggerApplicationComponent

class App: Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.factory().create()
    }

}