package com.codewithmohsen.domain.useCase

import com.codewithmohsen.domain.models.Category
import com.codewithmohsen.domain.repository.NewsListRepository
import javax.inject.Inject

class SetCategoryUseCase @Inject constructor(private val repository: NewsListRepository) {

    operator fun invoke(category: Category) = repository.setCategory(category)
}