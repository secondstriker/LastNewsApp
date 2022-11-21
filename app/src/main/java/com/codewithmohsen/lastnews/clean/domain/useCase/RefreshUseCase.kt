package com.codewithmohsen.lastnews.clean.domain.useCase

import com.codewithmohsen.lastnews.clean.domain.repository.NewsListRepository
import javax.inject.Inject

class RefreshUseCase @Inject constructor(private val repository: NewsListRepository) {

    suspend operator fun invoke() = repository.refresh()
}