package com.getusersapp.di.modules

import android.content.Context
import com.getusersapp.GetUsersAppApplication
import com.getusersapp.data.network.LearningsApi
import com.getusersapp.data.repositories.LearningsListRepository
import com.getusersapp.data.repositories.NewsRepository
import com.getusersapp.ui.albumslist.viewmodel.AlbumsViewModelFactory
import com.getusersapp.ui.learningslist.viewmodel.LearningsListViewModelFactory
import com.getusersapp.ui.getuserswithalbums.viewmodel.UsersWithCommentsViewModelFactory
import com.getusersapp.ui.news.viewmodel.NewsViewModelFactory
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class AppModule(
    private val application: GetUsersAppApplication
) {
    @Provides
    @Singleton
    fun providesApplicationContext(): Context = application


    @Provides
    @Singleton
    internal fun getUserRepository(learningsApi: LearningsApi): LearningsListRepository {
        return LearningsListRepository(learningsApi)
    }

    @Provides
    @Singleton
    internal fun getNewsRepository(learningsApi: LearningsApi): NewsRepository {
        return NewsRepository(learningsApi)
    }

    @Provides
    @Singleton
    internal fun getViewModelFactory(
        learningsListRepository: LearningsListRepository,
        compositeDisposable: CompositeDisposable
    ):
            LearningsListViewModelFactory {
        return LearningsListViewModelFactory(
            learningsListRepository,
            compositeDisposable
        )
    }

    @Provides
    @Singleton
    internal fun getUsersWithCommentsViewModelFactory(
        learningsListRepository: LearningsListRepository,
        compositeDisposable: CompositeDisposable
    ):
            UsersWithCommentsViewModelFactory {
        return UsersWithCommentsViewModelFactory(
            learningsListRepository,
            compositeDisposable
        )
    }

    @Provides
    @Singleton
    internal fun getAlbumsWithPhotosViewModelFactory(
        learningsListRepository: LearningsListRepository,
        compositeDisposable: CompositeDisposable
    ):
            AlbumsViewModelFactory {
        return AlbumsViewModelFactory(
            learningsListRepository,
            compositeDisposable
        )
    }

    @Provides
    @Singleton
    internal fun getNewsViewModelFactory(
        newsRepository: NewsRepository
    ):
            NewsViewModelFactory {
        return NewsViewModelFactory(
            newsRepository
        )
    }

    @Provides
    @Singleton
    internal fun getCompositeDisposable():
            CompositeDisposable {
        return CompositeDisposable()
    }
}