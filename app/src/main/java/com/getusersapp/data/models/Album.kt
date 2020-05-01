package com.getusersapp.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Album(
    val id: Int,
    val title: String,
    var photos: List<Photo>?
) : Parcelable