package com.getusersapp.ui.news.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.getusersapp.R
import com.getusersapp.data.models.NewsItem
import com.getusersapp.ui.news.adapter.viewholder.NewsViewHolder

class NewsListAdapter(
    private val newsItemList: List<NewsItem>
) : RecyclerView.Adapter<NewsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return NewsViewHolder(
            DataBindingUtil.inflate(
                inflater
                , R.layout.layout_news_item, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return newsItemList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.layoutNewsItemBinding.news = newsItemList[position]
    }
}