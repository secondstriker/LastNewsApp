package com.codewithmohsen.lastnews.data.di


import com.codewithmohsen.lastnews.data.NewsListRepositoryImpl
import com.codewithmohsen.domain.repository.NewsListRepository
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
    ): com.codewithmohsen.domain.repository.NewsListRepository

}