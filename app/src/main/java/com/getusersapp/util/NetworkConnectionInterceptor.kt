package com.getusersapp.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.getusersapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(
    context: Context
) : Interceptor {
    private val applicationContext = context.applicationContext
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isInternetAvailable()) {
            throw NoInternetException("Make sure you have an active network connection")
        }
        if(BuildConfig.FLAVOR.equals("news", true)){
            val original = chain.request()
            val request = original.newBuilder()
                .header("Authorization", "8d1a678717df4d23978bb7ed434be9bb")
                .method(original.method(), original.body())
                .build()
            return chain.proceed(request)
        } else {
            return chain.proceed(chain.request())
        }
    }

    private fun isInternetAvailable(): Boolean {
        val connectivityManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val nw = connectivityManager.activeNetwork ?: return false
        val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
        return when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
            else -> false
        }
    }
}