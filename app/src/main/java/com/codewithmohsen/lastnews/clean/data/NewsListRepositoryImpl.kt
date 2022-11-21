package com.codewithmohsen.lastnews.clean.data

import com.codewithmohsen.lastnews.api.ApiService
import com.codewithmohsen.lastnews.clean.common.Config
import com.codewithmohsen.lastnews.clean.common.Logger
import com.codewithmohsen.lastnews.clean.domain.Resource
import com.codewithmohsen.lastnews.clean.domain.api.APIErrorResponse
import com.codewithmohsen.lastnews.clean.domain.api.ErrorModel
import com.codewithmohsen.lastnews.clean.domain.api.NetworkResponse
import com.codewithmohsen.lastnews.clean.domain.di.CoroutinesScopesModule.ApplicationScope
import com.codewithmohsen.lastnews.clean.domain.di.IoDispatcher
import com.codewithmohsen.lastnews.clean.domain.models.Category
import com.codewithmohsen.lastnews.clean.domain.models.ResponseModel
import com.codewithmohsen.lastnews.clean.domain.repository.NewsListRepository
import com.codewithmohsen.lastnews.clean.presentation.uiModels.UiArticle
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsListRepositoryImpl @Inject constructor(
    config: Config,
    logger: Logger,
    private val apiService: ApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
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

    override val news: Flow<Resource<List<UiArticle>>>
        get() = super.getResultAsFlow()

    override suspend fun apiCall(): NetworkResponse<ResponseModel, APIErrorResponse<ErrorModel>> =
        apiService.fetchNews(category = category.name, page = super.page)
}