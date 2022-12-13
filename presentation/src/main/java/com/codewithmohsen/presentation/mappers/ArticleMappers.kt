package com.codewithmohsen.presentation.mappers

import com.codewithmohsen.domain.models.Article
import com.codewithmohsen.presentation.converters.Converters
import com.codewithmohsen.presentation.uiModels.UiArticle

fun Article.map(): UiArticle = UiArticle(
    title = this.title ?: "",
    url = this.url ?: "",
    urlToImage = this.urlToImage ?: "",
    sourceName = this.source?.name ?: "",
    publishTime = Converters.dateTimeToRequiredFormatForList(this.publishedAt) ?: "",
    author = this.author ?: "",
    content = this.content ?: "",
    description = this.description ?: ""
)