package com.codewithmohsen.lastnews.domain.repository

import com.codewithmohsen.lastnews.domain.Resource
import com.codewithmohsen.lastnews.domain.models.Category
import com.codewithmohsen.lastnews.presentation.uiModels.UiArticle
import kotlinx.coroutines.flow.Flow

interface NewsListRepository {

    suspend fun fetchMoreNews()
    fun setCategory(category: Category)
    suspend fun refresh()
    val news: Flow<Resource<List<UiArticle>>>
}