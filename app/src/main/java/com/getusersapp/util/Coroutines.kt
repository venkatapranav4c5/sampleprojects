package com.getusersapp.util

import com.getusersapp.data.network.ApiException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

object Coroutines {
    fun <T : Any> ioThenMain(
        work: suspend (() -> T?),
        callback: ((T?) -> Unit),
        errorCallback: ((message: String?) -> Unit)
    ) =
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val data = CoroutineScope(Dispatchers.IO).async rt@{
                    return@rt work()
                }.await()
                callback(data)
            } catch (e: ApiException) {
                errorCallback(e.message)
            }
        }
}