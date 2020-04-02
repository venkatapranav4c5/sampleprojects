package com.getusersapp.ui.getusers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.getusersapp.data.repositories.GetUsersRepository

@Suppress("UNCHECKED_CAST")
class GetUsersViewModelFactory(
    private val repository: GetUsersRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GetUsersViewModel(repository) as T
    }
}