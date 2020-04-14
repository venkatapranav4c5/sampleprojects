package com.getusersapp.ui.getusers

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.getusersapp.GetUsersAppApplication
import com.getusersapp.R
import com.getusersapp.data.models.LearningItemClickListener
import com.getusersapp.data.models.User
import com.getusersapp.ui.getusers.adapter.GetLearningsListAdapter
import com.getusersapp.ui.userlist.UserListActivity
import com.getusersapp.util.DataUtils
import com.getusersapp.util.hide
import com.getusersapp.util.show
import com.getusersapp.util.showToast
import kotlinx.android.synthetic.main.activity_get_users.*
import javax.inject.Inject

class GetUsersActivity : AppCompatActivity(), LearningItemClickListener {

    @Inject
    lateinit var factory: GetUsersViewModelFactory

    private lateinit var viewModel: GetUsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_users)
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
            it?.layoutManager = GridLayoutManager(this,2)
            it?.setHasFixedSize(true)
            it?.adapter = GetLearningsListAdapter(
                DataUtils.fetchLearningsList(),
                this@GetUsersActivity
            )
        }
    }

    private fun prepareViewModel() {
        viewModel = ViewModelProvider(this, factory).get(GetUsersViewModel::class.java)
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
        val intent = Intent(this@GetUsersActivity, UserListActivity::class.java)
        intent.putParcelableArrayListExtra("UserList", ArrayList(it))
        startActivity(intent)
    }

    override fun onItemClick(position: Int) {
        progressBar?.show()
        when (position) {
            0 -> viewModel.getUsersFromKotlinCoroutines()
            1 -> viewModel.getUsersFromRxJavaLiveDataStreams()
        }
    }
}
