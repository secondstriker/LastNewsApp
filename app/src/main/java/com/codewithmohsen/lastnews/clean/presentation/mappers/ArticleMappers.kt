package com.codewithmohsen.lastnews.clean.presentation.mappers

import com.codewithmohsen.lastnews.clean.domain.models.Article
import com.codewithmohsen.lastnews.clean.presentation.uiModels.UiArticle
import com.codewithmohsen.lastnews.clean.presentation.converters.Converters

fun Article.map(): UiArticle = UiArticle(
    title = this.title ?: "",
    url = this.url ?: "",
    urlToImage = this.urlToImage ?: "",
    sourceName = this.source?.name ?: "",
    publishTime = Converters.dateTimeToRequiredFormatForList(this.publishedAt),
    author = this.author ?: "",
    content = this.content ?: "",
    description = this.description ?: ""
)