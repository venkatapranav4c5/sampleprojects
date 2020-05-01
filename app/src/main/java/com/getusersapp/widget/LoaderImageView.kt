package com.getusersapp.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.getusersapp.R
import kotlinx.android.synthetic.main.layout_photo.view.*


class LoaderImageView : FrameLayout {

    private var mContext: Context

    constructor(context: Context) : super(context) {
        mContext = context
        initializeView()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        mContext = context
        initializeView()
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mContext = context
        initializeView()
    }

    private fun initializeView() {
        View.inflate(mContext, R.layout.layout_photo, this)
    }

    fun loadImageUrl(url: String?){
        url?.let {
            progress_bar.visibility = View.GONE
            Glide.with(this)
                .load(url)
                .into(imgPhoto)
        }
    }

}