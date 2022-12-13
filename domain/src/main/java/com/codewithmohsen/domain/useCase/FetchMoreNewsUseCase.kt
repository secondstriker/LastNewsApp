package com.codewithmohsen.domain.useCase

import com.codewithmohsen.domain.Resource
import com.codewithmohsen.domain.models.Article
import com.codewithmohsen.domain.repository.NewsListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchMoreNewsUseCase @Inject constructor(private val repository: NewsListRepository) {

    val news: Flow<Resource<List<Article>>> = repository.news
    suspend fun fetchMoreNews() = repository.fetchMoreNews()
}