package com.codewithmohsen.lastnews.di


import com.codewithmohsen.lastnews.clean.data.NewsListRepositoryImpl
import com.codewithmohsen.lastnews.clean.domain.repository.NewsListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {

    @Binds
    abstract fun bindNewsListRepository(
        newsListRepositoryImpl: NewsListRepositoryImpl
    ): NewsListRepository

}