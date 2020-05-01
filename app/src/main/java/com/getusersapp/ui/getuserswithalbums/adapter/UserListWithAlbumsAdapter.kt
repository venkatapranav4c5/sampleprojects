package com.getusersapp.ui.getuserswithalbums.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.getusersapp.R
import com.getusersapp.data.models.User
import com.getusersapp.data.models.listeners.UserItemClickListener

class UserListWithAlbumsAdapter(
    private val userItemClickListener: UserItemClickListener
) : RecyclerView.Adapter<UserWithAlbumsViewHolder>() {

    private lateinit var userList: List<User>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserWithAlbumsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return UserWithAlbumsViewHolder(
            DataBindingUtil.inflate(
                inflater
                , R.layout.layout_user_item_with_album, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserWithAlbumsViewHolder, position: Int) {
        holder.layoutUserItemWithAlbumBinding.user = userList[position]
        holder.itemView.setOnClickListener {
            userItemClickListener.onItemClick(userList[position])
        }
    }

    fun updateList(userList: List<User>) {
        this.userList = userList
    }

    fun updateUser(user: User) {
        if (userList.contains(user)) {
            val index = userList.indexOf(user)
            val oldUser = userList[index]
            oldUser.albums = user.albums
            notifyItemChanged(index)
        }
    }

}