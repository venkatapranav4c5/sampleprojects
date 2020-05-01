package com.getusersapp.data.models.listeners

import com.getusersapp.data.models.User

interface UserItemClickListener {

    fun onItemClick(user: User)
}