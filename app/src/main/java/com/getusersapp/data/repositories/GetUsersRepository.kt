package com.getusersapp.data.repositories

import com.getusersapp.data.models.User
import com.getusersapp.data.network.GetUsersApi
import com.getusersapp.data.network.SafeApiRequest
import io.reactivex.Observable

class GetUsersRepository(
    private val getUsersApi: GetUsersApi
) : SafeApiRequest() {
    suspend fun getUsersList() = apiRequest {
        getUsersApi.getUsersList()
    }

    fun getUsersListObservable(): Observable<List<User>> {
        return getUsersApi.getUsersListObservable()
    }
}