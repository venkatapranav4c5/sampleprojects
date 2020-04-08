package com.getusersapp.data.network

import com.getusersapp.data.models.User
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

interface GetUsersApi {

    @GET("users")
    suspend fun getUsersList(): Response<List<User>>

    @GET("users")
    fun getUsersListObservable(): Observable<List<User>>
}