package com.getusersapp.ui.learningslist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.getusersapp.data.repositories.LearningsListRepository
import io.reactivex.disposables.CompositeDisposable

@Suppress("UNCHECKED_CAST")
class LearningsListViewModelFactory(
    private val repository: LearningsListRepository,
    private val compositeDisposable: CompositeDisposable
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LearningsListViewModel(
            repository,
            compositeDisposable
        ) as T
    }
}