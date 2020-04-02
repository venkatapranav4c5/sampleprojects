package com.getusersapp.ui.getusers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.getusersapp.R
import com.getusersapp.data.network.GetUsersApi
import com.getusersapp.data.repositories.GetUsersRepository
import com.getusersapp.di.component.AppComponent
import com.getusersapp.di.component.DaggerAppComponent
import com.getusersapp.di.modules.ApiModule
import com.getusersapp.di.modules.AppModule
import com.getusersapp.ui.userlist.UserListActivity
import com.getusersapp.util.hide
import com.getusersapp.util.show
import javax.inject.Inject

class GetUsersActivity : AppCompatActivity() {

    private var btnGetUsers: Button? = null
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
        btnGetUsers = findViewById(R.id.btnGetUsers)
        progressBar = findViewById(R.id.progressBar)

        btnGetUsers!!.setOnClickListener {
            progressBar?.show()
            viewModel.getUsers()
        }
    }

    private fun prepareViewModel() {
        viewModel = ViewModelProvider(this, factory).get(GetUsersViewModel::class.java)
        viewModel.usersList.observe(this, Observer {
            progressBar?.hide()
            val intent = Intent(this@GetUsersActivity, UserListActivity::class.java)
            intent.putParcelableArrayListExtra("UserList", ArrayList(it))
            startActivity(intent)
        })
    }

}
