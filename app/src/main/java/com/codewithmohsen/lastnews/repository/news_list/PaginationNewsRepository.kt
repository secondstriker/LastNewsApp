package com.codewithmohsen.lastnews.repository.news_list

import com.codewithmohsen.lastnews.clean.common.Config
import com.codewithmohsen.lastnews.clean.common.Logger
import com.codewithmohsen.lastnews.clean.domain.di.CoroutinesScopesModule.ApplicationScope
import com.codewithmohsen.lastnews.clean.domain.di.IoDispatcher
import com.codewithmohsen.lastnews.clean.domain.models.Article
import com.codewithmohsen.lastnews.clean.domain.models.ResponseModel
import com.codewithmohsen.lastnews.clean.domain.repository.BaseOnlineRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import timber.log.Timber

abstract class PaginationNewsRepository(
    private val config: Config,
    private val logger: Logger,
    @ApplicationScope private val externalCoroutineScope: CoroutineScope,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : BaseOnlineRepository<ResponseModel, List<Article>>(config, logger, externalCoroutineScope, ioDispatcher) {

    var page: Int = 1
        private set

    override suspend fun bodyToResult(apiModel: ResponseModel?): List<Article> {
        val preResult = super.getResultAsFlow().value
        page++

        Timber.d("PaginationRepository")

        val result = if (!apiModel?.articles.isNullOrEmpty() && !preResult.data.isNullOrEmpty())
            preResult.data.plus(apiModel?.articles!!)
        else if (preResult.data.isNullOrEmpty())
            apiModel?.articles
        else preResult.data

        if (result.isNullOrEmpty())
            page = 1

        return result ?: mutableListOf()
    }

    protected fun reset() {
        page = 1
    }
}