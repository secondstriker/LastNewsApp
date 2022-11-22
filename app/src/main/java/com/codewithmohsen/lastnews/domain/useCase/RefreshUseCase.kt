package com.codewithmohsen.lastnews.domain.useCase

import com.codewithmohsen.lastnews.domain.repository.NewsListRepository
import javax.inject.Inject

class RefreshUseCase @Inject constructor(private val repository: NewsListRepository) {

    suspend operator fun invoke() = repository.refresh()
}