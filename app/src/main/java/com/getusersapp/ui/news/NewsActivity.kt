package com.getusersapp.ui.news;

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.getusersapp.GetUsersAppApplication
import com.getusersapp.R
import com.getusersapp.ui.news.adapter.NewsListAdapter
import com.getusersapp.ui.news.viewmodel.NewsViewModel
import com.getusersapp.ui.news.viewmodel.NewsViewModelFactory
import com.getusersapp.util.hide
import com.getusersapp.util.showToast
import kotlinx.android.synthetic.main.activity_learnings_list.*
import kotlinx.android.synthetic.main.activity_news.*
import javax.inject.Inject


class NewsActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: NewsViewModelFactory

    private lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        injectActivity()
        prepareViewModel()
        initViews()
    }

    private fun injectActivity() {
        getApp()?.appComponent?.inject(this)
    }

    private fun getApp(): GetUsersAppApplication? {
        return applicationContext as GetUsersAppApplication
    }

    private fun prepareViewModel() {
        viewModel = ViewModelProvider(this, factory).get(NewsViewModel::class.java)
        viewModel.newsItemList.observe(this, Observer { news ->
            newsListView.also {
                it?.layoutManager = LinearLayoutManager(this)
                it?.setHasFixedSize(true)
                it?.adapter = NewsListAdapter(news)
            }
        })
        viewModel.error.observe(this, Observer {
            showToast(it)
        })
    }

    private fun initViews() {
        searchView?.imeOptions = EditorInfo.IME_ACTION_DONE
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.getNews(query)
                clearSearchView()
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }

    private fun clearSearchView() {
        searchView?.setQuery("", false)
        searchView?.clearFocus()
    }
}
