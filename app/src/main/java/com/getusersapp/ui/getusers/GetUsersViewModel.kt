package com.getusersapp.ui.getusers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.getusersapp.util.Coroutines
import com.getusersapp.data.models.User
import com.getusersapp.data.repositories.GetUsersRepository
import kotlinx.coroutines.Job

class GetUsersViewModel(
    private val repository: GetUsersRepository
) : ViewModel() {
    private val _usersList = MutableLiveData<List<User>>()
    private lateinit var job: Job
    val usersList: LiveData<List<User>>
        get() = _usersList

    fun getUsers() {
        job = Coroutines.ioThenMain(
            { repository.getUsersList() },
            { _usersList.value = it }
        )
    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }
}