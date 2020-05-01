package com.getusersapp.ui.news.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.getusersapp.data.models.NewsItem
import com.getusersapp.data.repositories.NewsRepository
import com.getusersapp.util.Coroutines
import kotlinx.coroutines.Job
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class NewsViewModel(
    private val repository: NewsRepository
) : ViewModel() {

    private val _newsItemList = MutableLiveData<List<NewsItem>>()
    private lateinit var job: Job
    val newsItemList: LiveData<List<NewsItem>>
        get() = _newsItemList

    private val _error = MutableLiveData<String>()

    val error: LiveData<String>
        get() = _error

    @SuppressLint("NewApi")
    fun getNews(query: String) {
        job = Coroutines.ioThenMain(
            { repository.getNews(query) },
            {
                val format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
                val result = it?.articles?.sortedByDescending {
                    LocalDate.parse(it.publishedAt, format)
                }
                _newsItemList.value = result
            },{
                _error.value = it
            }
        )
    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }
}