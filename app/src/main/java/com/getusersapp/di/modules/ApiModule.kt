package com.getusersapp.di.modules

import com.getusersapp.data.network.GetUsersApi
import com.getusersapp.di.qualifiers.EndPoint
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
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
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
        @EndPoint endPoint: String
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .baseUrl(endPoint)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideGsonConverter(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    internal fun provideRxJavaFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    @EndPoint
    fun provideBaseURL(): String {
        return "https://jsonplaceholder.typicode.com/"
    }
}