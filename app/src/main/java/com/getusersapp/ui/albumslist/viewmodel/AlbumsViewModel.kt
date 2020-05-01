package com.getusersapp.ui.albumslist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.getusersapp.data.models.Album
import com.getusersapp.data.models.User
import com.getusersapp.data.repositories.LearningsListRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AlbumsViewModel(
    private val repository: LearningsListRepository,
    private val disposable: CompositeDisposable
) : ViewModel() {

    private val _albumsWithPhotosMutable = MutableLiveData<Album>()

    private val _error = MutableLiveData<String>()

    val albumsWithPhotosMutable: LiveData<Album>
        get() = _albumsWithPhotosMutable

    val error: LiveData<String>
        get() = _error

    fun getPhotosListFromAlbum(albumsList: List<Album>?) {
        disposable.add(Observable.fromIterable(albumsList)
            .subscribeOn(Schedulers.io())
            .flatMap {
                getPhotosListObservable(it)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _albumsWithPhotosMutable.value = it
            }, {
                _error.value = it.message
            })
        )
    }

    private fun getPhotosListObservable(album: Album): Observable<Album> {
        return repository.getPhotosListObservable(album.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                album.photos = it
                return@map album
            }
    }
}