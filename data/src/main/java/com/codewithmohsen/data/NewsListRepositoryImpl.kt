package com.codewithmohsen.data

import com.codewithmohsen.common.di.ApplicationScope
import com.codewithmohsen.common.di.IoDispatcher
import com.codewithmohsen.data.api.ApiService
import com.codewithmohsen.domain.api.APIErrorResponse
import com.codewithmohsen.domain.api.ErrorModel
import com.codewithmohsen.domain.api.NetworkResponse
import com.codewithmohsen.domain.Resource
import com.codewithmohsen.domain.models.Article
import com.codewithmohsen.domain.models.Category
import com.codewithmohsen.domain.models.ResponseModel
import com.codewithmohsen.domain.repository.NewsListRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class NewsListRepositoryImpl @Inject constructor(
    config: com.codewithmohsen.common.Config,
    logger: com.codewithmohsen.common.Logger,
    private val apiService: ApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @ApplicationScope private val externalCoroutineDispatcher: CoroutineScope
): PaginationNewsRepository(config, logger, externalCoroutineDispatcher, ioDispatcher),
    NewsListRepository {

    private lateinit var category: Category

    override suspend fun fetchMoreNews() { super.fetch(false) }
    override fun setCategory(category: Category) { this.category = category }
    override suspend fun refresh() {
        super.reset()
        super.fetch(true)
    }

    override val news: Flow<Resource<List<Article>>>
        get() = super.getResultAsFlow()

    override suspend fun apiCall(): NetworkResponse<ResponseModel, APIErrorResponse<ErrorModel>> =
        apiService.fetchNews(category = category.name, page = super.page)
}