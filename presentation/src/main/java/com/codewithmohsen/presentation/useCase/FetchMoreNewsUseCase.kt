package com.codewithmohsen.presentation.useCase

import androidx.lifecycle.Transformations.map
import com.codewithmohsen.domain.Resource
import com.codewithmohsen.domain.models.Article
import com.codewithmohsen.domain.repository.NewsListRepository
import com.codewithmohsen.presentation.mappers.map
import com.codewithmohsen.presentation.uiModels.UiArticle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FetchMoreNewsUseCase @Inject constructor(private val repository: NewsListRepository) {

    val news: Flow<Resource<List<UiArticle>>> =
        repository.news.map { resource ->
            Resource(resource.status, resource.data?.map { it.map() }, resource.message)
        }
    suspend fun fetchMoreNews() = repository.fetchMoreNews()
}