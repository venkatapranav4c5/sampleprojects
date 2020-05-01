package com.getusersapp.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photo(
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
) : Parcelable