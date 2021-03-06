package com.codewithmohsen.lastnews.repository.news_list

import com.codewithmohsen.lastnews.models.Article
import com.codewithmohsen.lastnews.models.Category
import com.codewithmohsen.lastnews.repository.Resource
import kotlinx.coroutines.flow.Flow

interface NewsListRepository {

    suspend fun fetchMoreNews()
    fun setCategory(category: Category)
    suspend fun refresh()
    val news: Flow<Resource<List<Article>>>
}