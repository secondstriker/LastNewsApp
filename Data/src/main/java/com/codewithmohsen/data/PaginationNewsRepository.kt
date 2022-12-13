package com.codewithmohsen.data

import com.codewithmohsen.common.Config
import com.codewithmohsen.common.Logger
import com.codewithmohsen.common.di.ApplicationScope
import com.codewithmohsen.common.di.IoDispatcher
import com.codewithmohsen.domain.models.Article
import com.codewithmohsen.domain.models.ResponseModel
import com.codewithmohsen.domain.repository.BaseOnlineRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import timber.log.Timber

abstract class PaginationNewsRepository(
    config: Config,
    logger: Logger,
    @ApplicationScope private val externalCoroutineScope: CoroutineScope,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : BaseOnlineRepository<ResponseModel, List<Article>>(config, logger, externalCoroutineScope, ioDispatcher) {

    var page: Int = 1
        private set

    override suspend fun bodyToResult(apiModel: ResponseModel?): List<Article> {
        val preResult = super.getResultAsFlow().value
        page++

        Timber.d("PaginationRepository")

        val newResult = apiModel?.articles
        val result = if (!newResult.isNullOrEmpty() && !preResult.data.isNullOrEmpty())
            preResult.data?.plus(newResult)
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