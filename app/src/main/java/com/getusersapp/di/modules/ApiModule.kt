package com.getusersapp.di.modules

import android.content.Context
import com.getusersapp.data.network.GetUsersApi
import com.getusersapp.di.qualifiers.EndPoint
import com.getusersapp.util.NetworkConnectionInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
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
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
        @EndPoint endPoint: String
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .baseUrl(endPoint)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideOkHttpInterceptor(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(NetworkConnectionInterceptor(context))
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
    internal fun provideBaseURL(): String {
        return "https://jsonplaceholder.typicode.com/"
    }
}