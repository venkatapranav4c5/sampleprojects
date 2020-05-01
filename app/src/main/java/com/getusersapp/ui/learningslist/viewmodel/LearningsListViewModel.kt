package com.getusersapp.ui.learningslist.viewmodel

import androidx.lifecycle.*
import com.getusersapp.util.Coroutines
import com.getusersapp.data.models.User
import com.getusersapp.data.repositories.LearningsListRepository
import com.getusersapp.util.getMiddleElement
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Job

class LearningsListViewModel(
    private val repository: LearningsListRepository,
    private val disposable: CompositeDisposable
) : ViewModel() {
    private val _usersListMutable = MutableLiveData<List<User>>()

    private val _error = MutableLiveData<String>()

    private lateinit var job: Job

    val usersListMutable: LiveData<List<User>>
        get() = _usersListMutable

    val error: LiveData<String>
        get() = _error

    fun getUsersFromKotlinCoroutines() {
        job = Coroutines.ioThenMain(
            { repository.getUsersList() },
            { _usersListMutable.value = it },
            { _error.value = it }
        )
    }

    fun getUsersFromRxJavaLiveDataStreams(showJustMiddleElement: Boolean) {
        disposable.add(
            repository.getUsersListObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    if (showJustMiddleElement) {
                        val userList = listOfNotNull(it.getMiddleElement())
                        _usersListMutable.value = userList
                    } else {
                        _usersListMutable.value = it
                    }
                }, {
                    _error.value = it.message
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
        if (disposable.size() > 0) disposable.clear()
    }
}