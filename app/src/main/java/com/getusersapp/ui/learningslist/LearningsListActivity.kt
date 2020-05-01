package com.getusersapp.ui.learningslist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.getusersapp.BuildConfig
import com.getusersapp.GetUsersAppApplication
import com.getusersapp.R
import com.getusersapp.data.models.listeners.LearningItemClickListener
import com.getusersapp.data.models.User
import com.getusersapp.ui.learningslist.adapter.LearningsListAdapter
import com.getusersapp.ui.learningslist.viewmodel.LearningsListViewModel
import com.getusersapp.ui.learningslist.viewmodel.LearningsListViewModelFactory
import com.getusersapp.ui.getuserswithalbums.UsersWithAlbumsActivity
import com.getusersapp.ui.news.NewsActivity
import com.getusersapp.ui.userlist.UserListActivity
import com.getusersapp.util.DataUtils
import com.getusersapp.util.hide
import com.getusersapp.util.show
import com.getusersapp.util.showToast
import kotlinx.android.synthetic.main.activity_learnings_list.*
import javax.inject.Inject

class LearningsListActivity : AppCompatActivity(),
    LearningItemClickListener {

    @Inject
    lateinit var factory: LearningsListViewModelFactory

    private lateinit var viewModel: LearningsListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learnings_list)
        injectActivity()
        initViews()
        prepareViewModel()
    }

    private fun injectActivity() {
        getApp()?.appComponent?.inject(this)
    }

    private fun getApp(): GetUsersAppApplication? {
        return applicationContext as GetUsersAppApplication
    }

    private fun initViews() {
        learningsListView.also {
            it?.setHasFixedSize(true)
            it?.adapter = LearningsListAdapter(
                DataUtils.fetchLearningsList(),
                this@LearningsListActivity
            )
        }
    }

    private fun prepareViewModel() {
        viewModel = ViewModelProvider(this, factory).get(LearningsListViewModel::class.java)
        viewModel.usersListMutable.observe(this, Observer {
            progressBar?.hide()
            navigateToUserListScreen(it)
        })

        viewModel.error.observe(this, Observer {
            progressBar?.hide()
            showToast(it)
        })
    }

    private fun navigateToUserListScreen(it: List<User>) {
        val intent = Intent(this@LearningsListActivity, UserListActivity::class.java)
        intent.putParcelableArrayListExtra("UserList", ArrayList(it))
        startActivity(intent)
    }

    private fun navigateToUserWithAlbumsScreen() {
        val intent = Intent(this@LearningsListActivity, UsersWithAlbumsActivity::class.java)
        startActivity(intent)
    }

    override fun onItemClick(position: Int) {
        progressBar?.show()
        if (BuildConfig.FLAVOR.equals("users", true)) {
            handleUsersItemClickPosition(position)
        } else {
            handleNewsItemClickPosition(position)
        }

    }

    private fun handleNewsItemClickPosition(position: Int) {
        when (position) {
            0 -> {
                progressBar?.show()
                val intent = Intent(this@LearningsListActivity, NewsActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun handleUsersItemClickPosition(position: Int) {
        when (position) {
            0 -> {
                progressBar?.show()
                viewModel.getUsersFromKotlinCoroutines()
            }
            1 -> {
                progressBar?.show()
                viewModel.getUsersFromRxJavaLiveDataStreams(false)
            }
            2 -> {
                progressBar?.show()
                viewModel.getUsersFromRxJavaLiveDataStreams(true)
            }
            3 -> navigateToUserWithAlbumsScreen()
        }
    }

    override fun onResume() {
        super.onResume()
        progressBar?.hide()
    }
}
