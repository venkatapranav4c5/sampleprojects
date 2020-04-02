package com.getusersapp.di.component

import com.getusersapp.di.modules.ApiModule
import com.getusersapp.di.modules.AppModule
import com.getusersapp.ui.getusers.GetUsersActivity
import com.getusersapp.ui.getusers.GetUsersViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ApiModule::class])
interface AppComponent {
    fun getUsersViewModelFactory(): GetUsersViewModelFactory
    fun inject(getUsersActivity: GetUsersActivity)
}