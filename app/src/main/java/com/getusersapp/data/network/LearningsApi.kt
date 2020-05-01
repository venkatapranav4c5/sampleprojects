package com.getusersapp.data.network

import com.getusersapp.data.models.Album
import com.getusersapp.data.models.NewsList
import com.getusersapp.data.models.Photo
import com.getusersapp.data.models.User
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LearningsApi {

    // Users Related API List
    @GET("users")
    suspend fun getUsersList(): Response<List<User>>

    @GET("users")
    fun getUsersListObservable(): Observable<List<User>>

    @GET("users/{id}/albums")
    fun getAlbumsListObservable(@Path("id") id: Int): Observable<List<Album>>

    @GET("albums/{id}/photos")
    fun getPhotosListObservable(@Path("id") id: Int): Observable<List<Photo>>

    //News Related API List
    @GET("everything")
    suspend fun getNewsList(@Query("q") type: String, @Query("pageSize") pageSize: Int): Response<NewsList>
}