package com.getusersapp.ui.userlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.getusersapp.*
import com.getusersapp.data.models.User
import com.getusersapp.ui.userlist.adapter.UserListAdapter
import com.getusersapp.util.hide
import com.getusersapp.util.hideList
import com.getusersapp.util.show
import com.getusersapp.util.showList
import kotlinx.android.synthetic.main.activity_user_list.*
import java.util.ArrayList

class UserListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)
        checkBundle()
    }

    private fun checkBundle() {
        val bundle: Bundle? = intent.extras
        val userList = bundle?.getParcelableArrayList<User>("UserList")
        userList?.let {
            setListAdapter(it)
            return
        }
        userListView?.hideList()
        errorTextView?.show()
    }

    private fun setListAdapter(userList: ArrayList<User>) {
        userListView?.showList()
        errorTextView?.hide()
        userListView.also {
            it?.layoutManager = LinearLayoutManager(this)
            it?.setHasFixedSize(true)
            it?.adapter =
                UserListAdapter(userList)
        }
    }
}
