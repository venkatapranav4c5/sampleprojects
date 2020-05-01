package com.getusersapp.di.component

import com.getusersapp.di.modules.ApiModule
import com.getusersapp.di.modules.AppModule
import com.getusersapp.ui.albumslist.AlbumsListActivity
import com.getusersapp.ui.albumslist.viewmodel.AlbumsViewModelFactory
import com.getusersapp.ui.learningslist.LearningsListActivity
import com.getusersapp.ui.learningslist.viewmodel.LearningsListViewModelFactory
import com.getusersapp.ui.getuserswithalbums.UsersWithAlbumsActivity
import com.getusersapp.ui.getuserswithalbums.viewmodel.UsersWithCommentsViewModelFactory
import com.getusersapp.ui.news.NewsActivity
import com.getusersapp.ui.news.viewmodel.NewsViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ApiModule::class])
interface AppComponent {
    fun getUsersViewModelFactory(): LearningsListViewModelFactory
    fun getUsersWithCommentsViewModelFactory(): UsersWithCommentsViewModelFactory
    fun getAlbumsWithPhotosViewModelFactory(): AlbumsViewModelFactory
    fun getNewsViewModelFactory(): NewsViewModelFactory

    fun inject(learningsListActivity: LearningsListActivity)
    fun inject(usersWithAlbumsActivity: UsersWithAlbumsActivity)
    fun inject(albumsActivity: AlbumsListActivity)
    fun inject(newsActivity: NewsActivity)
}