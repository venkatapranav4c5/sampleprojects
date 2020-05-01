package com.getusersapp.data.repositories

import com.getusersapp.data.network.LearningsApi
import com.getusersapp.data.network.SafeApiRequest

class NewsRepository(
    private val learningsApi: LearningsApi
) : SafeApiRequest() {

    suspend fun getNews(query: String) = apiRequest {
        learningsApi.getNewsList(query, 50)
    }
}