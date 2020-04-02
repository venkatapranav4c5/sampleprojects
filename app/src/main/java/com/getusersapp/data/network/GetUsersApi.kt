package com.getusersapp.data.network

import com.getusersapp.data.models.User
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface GetUsersApi {

    @GET("users")
    suspend fun getUsersList(): Response<List<User>>
}