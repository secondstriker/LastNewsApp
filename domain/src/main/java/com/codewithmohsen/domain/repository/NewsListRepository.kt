package com.codewithmohsen.domain.repository

import com.codewithmohsen.domain.Resource
import com.codewithmohsen.domain.models.Article
import com.codewithmohsen.domain.models.Category
import kotlinx.coroutines.flow.Flow

interface NewsListRepository {

    suspend fun fetchMoreNews()
    fun setCategory(category: Category)
    suspend fun refresh()
    val news: Flow<Resource<List<Article>>>
}