package com.getusersapp.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class User(
    val id: Int,
    val name: String,
    val email: String,
    val phone: String,
    var albums: List<Album>?
) : Parcelable