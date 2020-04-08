package com.getusersapp.util

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.showList() {
    visibility = View.VISIBLE
}

fun RecyclerView.hideList() {
    visibility = View.GONE
}

fun TextView.show() {
    visibility = View.VISIBLE
}

fun TextView.hide() {
    visibility = View.GONE
}

fun ProgressBar.show() {
    visibility = View.VISIBLE
}

fun ProgressBar.hide() {
    visibility = View.GONE
}

fun Button.enable() {
    isEnabled = true
    isClickable = true
    alpha = 1.0f
}

fun Button.disable() {
    isEnabled = false
    isClickable = false
    alpha = 0.50f
}

fun Context.showToast(message: String?) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}
