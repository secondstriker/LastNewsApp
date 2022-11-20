package com.codewithmohsen.lastnews.clean.domain.repository

import com.codewithmohsen.lastnews.clean.domain.models.Article
import com.codewithmohsen.lastnews.clean.domain.models.Category
import com.codewithmohsen.lastnews.clean.domain.Resource
import kotlinx.coroutines.flow.Flow

interface NewsListRepository {

    suspend fun fetchMoreNews()
    fun setCategory(category: Category)
    suspend fun refresh()
    val news: Flow<Resource<List<Article>>>
}