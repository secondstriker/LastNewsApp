package com.codewithmohsen.lastnews.presentation.mappers

import com.codewithmohsen.domain.models.Article
import com.codewithmohsen.lastnews.presentation.converters.Converters
import com.codewithmohsen.lastnews.presentation.uiModels.UiArticle

fun com.codewithmohsen.domain.models.Article.map(): UiArticle = UiArticle(
    title = this.title ?: "",
    url = this.url ?: "",
    urlToImage = this.urlToImage ?: "",
    sourceName = this.source?.name ?: "",
    publishTime = Converters.dateTimeToRequiredFormatForList(this.publishedAt) ?: "",
    author = this.author ?: "",
    content = this.content ?: "",
    description = this.description ?: ""
)