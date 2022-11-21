package com.codewithmohsen.lastnews.clean.domain.useCase

import com.codewithmohsen.lastnews.clean.domain.Resource
import com.codewithmohsen.lastnews.clean.domain.repository.NewsListRepository
import com.codewithmohsen.lastnews.clean.presentation.uiModels.UiArticle
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchMoreNewsUseCase @Inject constructor(private val repository: NewsListRepository) {

    val news: Flow<Resource<List<UiArticle>>> = repository.news
    suspend fun fetchMoreNews() = repository.fetchMoreNews()
}