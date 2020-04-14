package com.getusersapp

import android.app.Application
import com.getusersapp.di.component.AppComponent
import com.getusersapp.di.component.DaggerAppComponent
import com.getusersapp.di.modules.AppModule

class GetUsersAppApplication : Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }
}