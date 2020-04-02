package com.getusersapp.di.modules

import com.getusersapp.data.network.GetUsersApi
import com.getusersapp.di.qualifiers.EndPoint
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    internal fun providesGetUsersApi(retrofit: Retrofit): GetUsersApi {
        return retrofit.create(GetUsersApi::class.java)
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        @EndPoint endPoint: String
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(gsonConverterFactory)
            .baseUrl(endPoint)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideGsonConverter(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @EndPoint
    fun provideBaseURL(): String {
        return "https://jsonplaceholder.typicode.com/"
    }
}