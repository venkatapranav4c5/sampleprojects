package com.getusersapp.ui.albumslist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.getusersapp.data.repositories.LearningsListRepository
import com.getusersapp.ui.getuserswithalbums.viewmodel.UserWithAlbumsViewModel
import io.reactivex.disposables.CompositeDisposable

@Suppress("UNCHECKED_CAST")
class AlbumsViewModelFactory(
    private val repository: LearningsListRepository,
    private val compositeDisposable: CompositeDisposable
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AlbumsViewModel(
            repository,
            compositeDisposable
        ) as T
    }
}