package com.example.lesson6.di

import android.app.Application
import com.example.lesson6.App
import com.example.lesson6.presentation.MainActivity
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(activity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(): ApplicationComponent
    }

}