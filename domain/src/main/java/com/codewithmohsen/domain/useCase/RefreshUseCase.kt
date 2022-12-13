package com.codewithmohsen.domain.useCase

import com.codewithmohsen.domain.repository.NewsListRepository
import javax.inject.Inject

class RefreshUseCase @Inject constructor(private val repository: NewsListRepository) {

    suspend operator fun invoke() = repository.refresh()
}