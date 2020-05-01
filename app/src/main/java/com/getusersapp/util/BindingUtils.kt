package com.getusersapp.util

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.getusersapp.widget.LoaderImageView
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@BindingAdapter("android:src")
fun setImageViewResource(
    imageView: ImageView,
    resource: Int
) {
    imageView.setImageResource(resource)
}

@BindingAdapter("loadImage")
fun setImageWithLoader(
    loaderImageView: LoaderImageView,
    imageUrl: String?
) {
    loaderImageView.loadImageUrl(imageUrl)
}

@BindingAdapter("image")
fun loadImage(
    view: ImageView,
    url: String?) {
    url?.let {
        Glide.with(view)
            .load(url)
            .into(view)
    }
}

@SuppressLint("NewApi")
@BindingAdapter("time")
fun convertTime(view: TextView,
                time: String){
    val format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
    val localDateTime = LocalDateTime.parse(time, format)
    val formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy", Locale.ENGLISH)
    val date = formatter.format(localDateTime)
    view.text = date
}
