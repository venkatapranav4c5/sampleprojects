package com.getusersapp.ui.getusers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.getusersapp.R
import com.getusersapp.data.models.User
import com.getusersapp.data.network.GetUsersApi
import com.getusersapp.data.repositories.GetUsersRepository
import com.getusersapp.di.component.AppComponent
import com.getusersapp.di.component.DaggerAppComponent
import com.getusersapp.di.modules.ApiModule
import com.getusersapp.di.modules.AppModule
import com.getusersapp.ui.userlist.UserListActivity
import com.getusersapp.util.*
import javax.inject.Inject

class GetUsersActivity : AppCompatActivity() {

    private var btnGetUsersKotlinCoroutines: Button? = null
    private var btnGetUsersRxJavaLiveData: Button? = null
    private var progressBar: ProgressBar? = null

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
        val appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule())
            .build()
        appComponent.inject(this)
    }

    private fun initViews() {
        btnGetUsersKotlinCoroutines = findViewById(R.id.btnGetUsersKotlinCoroutines)
        btnGetUsersRxJavaLiveData = findViewById(R.id.btnGetUsersRxJavaLiveData)
        progressBar = findViewById(R.id.progressBar)

        btnGetUsersKotlinCoroutines!!.setOnClickListener {
            progressBar?.show()
            btnGetUsersRxJavaLiveData!!.disable()
            btnGetUsersKotlinCoroutines!!.enable()
            viewModel.getUsersFromKotlinCoroutines()
        }

        btnGetUsersRxJavaLiveData!!.setOnClickListener {
            progressBar?.show()
            btnGetUsersKotlinCoroutines!!.disable()
            btnGetUsersRxJavaLiveData!!.enable()
            viewModel.getUsersFromRxJavaLiveDataStreams()
        }
    }

    private fun prepareViewModel() {
        viewModel = ViewModelProvider(this, factory).get(GetUsersViewModel::class.java)
        viewModel.usersListMutable.observe(this, Observer {
            progressBar?.hide()
            navigateToUserListScreen(it)
        })

        viewModel.usersListRxJava.observe(this, Observer {
            progressBar?.hide()
            navigateToUserListScreen(it)
        })

        viewModel.error.observe(this, Observer {
            progressBar?.hide()
            showToast(it)
            btnGetUsersKotlinCoroutines!!.enable()
            btnGetUsersRxJavaLiveData!!.enable()
        })
    }

    private fun navigateToUserListScreen(it: List<User>) {
        val intent = Intent(this@GetUsersActivity, UserListActivity::class.java)
        intent.putParcelableArrayListExtra("UserList", ArrayList(it))
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        btnGetUsersKotlinCoroutines!!.enable()
        btnGetUsersRxJavaLiveData!!.enable()
    }

}
