package com.getusersapp.data.network

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class NetworkInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val request = original.newBuilder()
            .header("Authorization", "8d1a678717df4d23978bb7ed434be9bb")
            .method(original.method(), original.body())
            .build()
        return chain.proceed(request)
    }
}