package com.getusersapp.ui.albumslist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.getusersapp.GetUsersAppApplication
import com.getusersapp.R
import com.getusersapp.data.models.Album
import com.getusersapp.data.models.User
import com.getusersapp.ui.albumslist.adapter.AlbumsListAdapter
import com.getusersapp.ui.albumslist.viewmodel.AlbumsViewModel
import com.getusersapp.ui.albumslist.viewmodel.AlbumsViewModelFactory
import com.getusersapp.util.*
import kotlinx.android.synthetic.main.activity_albums_list.*
import kotlinx.android.synthetic.main.activity_albums_list.errorTextView
import javax.inject.Inject

class AlbumsListActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: AlbumsViewModelFactory

    private lateinit var viewModel: AlbumsViewModel

    private lateinit var adapter: AlbumsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albums_list)
        injectActivity()
        prepareViewModel()
        adapter = AlbumsListAdapter()
        checkBundle()
    }

    private fun injectActivity() {
        getApp()?.appComponent?.inject(this)
    }

    private fun getApp(): GetUsersAppApplication? {
        return applicationContext as GetUsersAppApplication
    }

    private fun checkBundle() {
        val bundle: Bundle? = intent.extras
        val user = bundle?.getParcelable<User>("User")
        user?.let {
            setListAdapter(user.albums)
            return
        }
        albumsListView?.hideList()
        errorTextView?.show()
    }

    private fun prepareViewModel() {
        viewModel = ViewModelProvider(this, factory).get(AlbumsViewModel::class.java)
        viewModel.albumsWithPhotosMutable.observe(this, Observer { album ->
            albumsListView.also {
                adapter.updateAlbum(album)
            }
        })
        viewModel.error.observe(this, Observer {
            showToast(it)
        })
    }

    private fun setListAdapter(albumsList: List<Album>?) {
        albumsList?.let{
            albumsListView?.showList()
            errorTextView?.hide()
            albumsListView.also {
                it?.setHasFixedSize(true)
                adapter.updateList(albumsList)
                it?.adapter = adapter
                viewModel.getPhotosListFromAlbum(albumsList)
            }
        }
    }
}

