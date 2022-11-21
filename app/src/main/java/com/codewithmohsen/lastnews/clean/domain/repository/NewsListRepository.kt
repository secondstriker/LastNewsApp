package com.codewithmohsen.lastnews.clean.domain.repository

import com.codewithmohsen.lastnews.clean.domain.Resource
import com.codewithmohsen.lastnews.clean.domain.models.Category
import com.codewithmohsen.lastnews.clean.presentation.uiModels.UiArticle
import kotlinx.coroutines.flow.Flow

interface NewsListRepository {

    suspend fun fetchMoreNews()
    fun setCategory(category: Category)
    suspend fun refresh()
    val news: Flow<Resource<List<UiArticle>>>
}