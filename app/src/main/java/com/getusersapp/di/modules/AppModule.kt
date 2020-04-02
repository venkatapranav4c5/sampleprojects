package com.getusersapp.di.modules

import com.getusersapp.data.network.GetUsersApi
import com.getusersapp.data.repositories.GetUsersRepository
import com.getusersapp.ui.getusers.GetUsersViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    internal fun getUserRepository(getUsersApi: GetUsersApi): GetUsersRepository {
        return GetUsersRepository(getUsersApi)
    }

    @Provides
    @Singleton
    internal fun getViewModelFactory(getUsersRepository: GetUsersRepository):
            GetUsersViewModelFactory {
        return GetUsersViewModelFactory(getUsersRepository)
    }
}