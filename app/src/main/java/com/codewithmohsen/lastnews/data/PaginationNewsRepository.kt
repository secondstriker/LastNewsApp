package com.codewithmohsen.lastnews.data

import com.codewithmohsen.commonandroid.di.CoroutinesScopesModule.ApplicationScope
import com.codewithmohsen.lastnews.presentation.mappers.map
import com.codewithmohsen.lastnews.presentation.uiModels.UiArticle
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import timber.log.Timber

abstract class PaginationNewsRepository(
    config: com.codewithmohsen.common.Config,
    logger: com.codewithmohsen.common.Logger,
    @ApplicationScope private val externalCoroutineScope: CoroutineScope,
    @com.codewithmohsen.common.di.IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : com.codewithmohsen.domain.repository.BaseOnlineRepository<com.codewithmohsen.domain.models.ResponseModel, List<UiArticle>>(config, logger, externalCoroutineScope, ioDispatcher) {

    var page: Int = 1
        private set

    override suspend fun bodyToResult(apiModel: com.codewithmohsen.domain.models.ResponseModel?): List<UiArticle> {
        val preResult = super.getResultAsFlow().value
        page++

        Timber.d("PaginationRepository")

        val newResult = apiModel?.articles?.map { it.map() }
        val result = if (!newResult.isNullOrEmpty() && !preResult.data.isNullOrEmpty())
            preResult.data.plus(newResult)
        else if (preResult.data.isNullOrEmpty())
            newResult
        else
            preResult.data

        if (result.isNullOrEmpty())
            page = 1

        return result ?: mutableListOf()
    }

    protected fun reset() {
        page = 1
    }
}