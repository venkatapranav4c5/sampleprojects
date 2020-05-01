package com.getusersapp.data.repositories

import com.getusersapp.data.models.Album
import com.getusersapp.data.models.Photo
import com.getusersapp.data.models.User
import com.getusersapp.data.network.LearningsApi
import com.getusersapp.data.network.SafeApiRequest
import io.reactivex.Observable

class LearningsListRepository(
    private val learningsApi: LearningsApi
) : SafeApiRequest() {
    suspend fun getUsersList() = apiRequest {
        learningsApi.getUsersList()
    }

    fun getUsersListObservable(): Observable<List<User>> {
        return learningsApi.getUsersListObservable()
    }

    fun getAlbumsListObservable(id: Int): Observable<List<Album>> {
        return learningsApi.getAlbumsListObservable(id)
    }

    fun getPhotosListObservable(albumId: Int): Observable<List<Photo>> {
        return learningsApi.getPhotosListObservable(albumId)
    }
}