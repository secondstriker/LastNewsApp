package com.codewithmohsen.lastnews.data

import com.codewithmohsen.lastnews.data.remote.api.ApiService
import com.codewithmohsen.lastnews.common.Config
import com.codewithmohsen.lastnews.common.Logger
import com.codewithmohsen.lastnews.domain.Resource
import com.codewithmohsen.lastnews.domain.api.APIErrorResponse
import com.codewithmohsen.lastnews.domain.api.ErrorModel
import com.codewithmohsen.lastnews.domain.api.NetworkResponse
import com.codewithmohsen.lastnews.domain.di.CoroutinesScopesModule.ApplicationScope
import com.codewithmohsen.lastnews.domain.di.IoDispatcher
import com.codewithmohsen.lastnews.domain.models.Category
import com.codewithmohsen.lastnews.domain.models.ResponseModel
import com.codewithmohsen.lastnews.domain.repository.NewsListRepository
import com.codewithmohsen.lastnews.presentation.uiModels.UiArticle
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@ViewModelScoped
class NewsListRepositoryImpl @Inject constructor(
    config: Config,
    logger: Logger,
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

    override val news: Flow<Resource<List<UiArticle>>>
        get() = super.getResultAsFlow()

    override suspend fun apiCall(): NetworkResponse<ResponseModel, APIErrorResponse<ErrorModel>> =
        apiService.fetchNews(category = category.name, page = super.page)
}