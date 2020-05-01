package com.getusersapp.ui.getuserswithalbums.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.getusersapp.data.models.User
import com.getusersapp.data.repositories.LearningsListRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UserWithAlbumsViewModel(
    private val repository: LearningsListRepository,
    private val disposable: CompositeDisposable
) : ViewModel() {

    private val _usersListMutable = MutableLiveData<List<User>>()

    private val _userWithAlbumsMutable = MutableLiveData<User>()

    private val _error = MutableLiveData<String>()

    val usersListMutable: LiveData<List<User>>
        get() = _usersListMutable

    val userWithAlbumsMutable: LiveData<User>
        get() = _userWithAlbumsMutable

    val error: LiveData<String>
        get() = _error


    fun getUsersListWithAlbums() {
        disposable.add(
            getUserObservable()
                .subscribeOn(Schedulers.io())
                .flatMap {
                    getAlbumsListObservable(it)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _userWithAlbumsMutable.value = it
                }, {
                    _error.value = it.message
                })
        )
    }

    private fun getUserObservable(): Observable<User> {
        return repository.getUsersListObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap {
                _usersListMutable.value = it
                Observable.fromIterable(it)
                    .subscribeOn(Schedulers.io())
            }
    }

    private fun getAlbumsListObservable(user: User): Observable<User> {
        return repository.getAlbumsListObservable(user.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                user.albums = it
                return@map user
            }
    }

    override fun onCleared() {
        super.onCleared()
        if (disposable.size() > 0) disposable.clear()
    }
}