package com.codewithmohsen.data.di


import com.codewithmohsen.data.NewsListRepositoryImpl
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
    ): NewsListRepository

}