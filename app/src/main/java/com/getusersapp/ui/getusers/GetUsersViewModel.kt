package com.getusersapp.ui.getusers

import androidx.lifecycle.*
import com.getusersapp.util.Coroutines
import com.getusersapp.data.models.User
import com.getusersapp.data.repositories.GetUsersRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Job

class GetUsersViewModel(
    private val repository: GetUsersRepository
) : ViewModel() {

    //Used By Coroutines
    private val _usersListMutable = MutableLiveData<List<User>>()

    //Used By RxJava
    private val _usersListRxJava = MutableLiveData<List<User>>()

    private val _error = MutableLiveData<String>()

    private var disposables = CompositeDisposable()

    private lateinit var job: Job

    val usersListMutable: LiveData<List<User>>
        get() = _usersListMutable

    val usersListRxJava: LiveData<List<User>>
        get() = _usersListRxJava

    val error: LiveData<String>
        get() = _error

    fun getUsersFromKotlinCoroutines() {
        job = Coroutines.ioThenMain(
            { repository.getUsersList() },
            { _usersListMutable.value = it },
            { _error.value = it }
        )
    }

    fun getUsersFromRxJavaLiveDataStreams() {
        disposables.add(
            repository.getUsersListObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    _usersListRxJava.value = it
                }, {
                    _error.value = it.message
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
        if (disposables.size() > 0) disposables.clear()
    }
}