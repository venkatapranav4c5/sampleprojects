package com.getusersapp.data.repositories

import com.getusersapp.data.network.GetUsersApi
import com.getusersapp.data.network.SafeApiRequest

class GetUsersRepository(
    private val getUsersApi: GetUsersApi
) : SafeApiRequest() {
    suspend fun getUsersList() = apiRequest {
        getUsersApi.getUsersList()
    }
}