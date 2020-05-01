package com.getusersapp.ui.getuserswithalbums

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.getusersapp.GetUsersAppApplication
import com.getusersapp.R
import com.getusersapp.data.models.User
import com.getusersapp.data.models.listeners.UserItemClickListener
import com.getusersapp.ui.albumslist.AlbumsListActivity
import com.getusersapp.ui.getuserswithalbums.adapter.UserListWithAlbumsAdapter
import com.getusersapp.ui.getuserswithalbums.viewmodel.UserWithAlbumsViewModel
import com.getusersapp.ui.getuserswithalbums.viewmodel.UsersWithCommentsViewModelFactory
import com.getusersapp.ui.userlist.UserListActivity
import com.getusersapp.util.*
import kotlinx.android.synthetic.main.activity_albums_list.*
import kotlinx.android.synthetic.main.activity_learnings_list.progressBar
import kotlinx.android.synthetic.main.activity_users_with_albums.*
import javax.inject.Inject

class UsersWithAlbumsActivity : AppCompatActivity(), UserItemClickListener {

    @Inject
    lateinit var factory: UsersWithCommentsViewModelFactory

    private lateinit var viewModel: UserWithAlbumsViewModel

    private lateinit var adapter: UserListWithAlbumsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_with_albums)
        injectActivity()
        prepareViewModel()
        initViews()
        fetchUserListWithAlbums()
    }

    private fun fetchUserListWithAlbums() {
        viewModel.getUsersListWithAlbums()
    }

    private fun initViews() {
        adapter =
            UserListWithAlbumsAdapter(this)
        progressBar?.show()
        userWithAlbumsListView?.hideList()
    }

    private fun injectActivity() {
        getApp()?.appComponent?.inject(this)
    }

    private fun getApp(): GetUsersAppApplication? {
        return applicationContext as GetUsersAppApplication
    }

    private fun prepareViewModel() {
        viewModel = ViewModelProvider(this, factory).get(UserWithAlbumsViewModel::class.java)
        viewModel.usersListMutable.observe(this, Observer {
            progressBar?.hide()
            setListAdapter(it)
        })

        viewModel.userWithAlbumsMutable.observe(this, Observer { user ->
            userWithAlbumsListView.also {
                adapter.updateUser(user)
            }
        })

        viewModel.error.observe(this, Observer {
            progressBar?.hide()
            showToast(it)
        })
    }

    private fun setListAdapter(userList: List<User>) {
        userWithAlbumsListView?.showList()
        errorTextView?.hide()
        userWithAlbumsListView.also {
            it?.setHasFixedSize(true)
            adapter.updateList(userList)
            it?.adapter = adapter
        }
    }

    override fun onItemClick(user: User) {
        val intent = Intent(this@UsersWithAlbumsActivity, AlbumsListActivity::class.java)
        intent.putExtra("User", user)
        startActivity(intent)
    }
}
