package com.codewithmohsen.lastnews.data

import com.codewithmohsen.lastnews.data.remote.api.ApiService
import com.codewithmohsen.commonandroid.di.CoroutinesScopesModule.ApplicationScope
import com.codewithmohsen.lastnews.presentation.uiModels.UiArticle
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
    @com.codewithmohsen.common.di.IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @ApplicationScope private val externalCoroutineDispatcher: CoroutineScope
): PaginationNewsRepository(config, logger, externalCoroutineDispatcher, ioDispatcher),
    com.codewithmohsen.domain.repository.NewsListRepository {

    private lateinit var category: com.codewithmohsen.domain.models.Category

    override suspend fun fetchMoreNews() { super.fetch(false) }
    override fun setCategory(category: com.codewithmohsen.domain.models.Category) { this.category = category }
    override suspend fun refresh() {
        super.reset()
        super.fetch(true)
    }

    override val news: Flow<com.codewithmohsen.domain.Resource<List<UiArticle>>>
        get() = super.getResultAsFlow()

    override suspend fun apiCall(): com.codewithmohsen.domain.api.NetworkResponse<com.codewithmohsen.domain.models.ResponseModel, com.codewithmohsen.domain.api.APIErrorResponse<com.codewithmohsen.domain.api.ErrorModel>> =
        apiService.fetchNews(category = category.name, page = super.page)
}