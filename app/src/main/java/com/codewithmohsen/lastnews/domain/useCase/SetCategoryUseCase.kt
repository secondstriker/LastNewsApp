package com.codewithmohsen.lastnews.domain.useCase

import com.codewithmohsen.lastnews.domain.models.Category
import com.codewithmohsen.lastnews.domain.repository.NewsListRepository
import javax.inject.Inject

class SetCategoryUseCase @Inject constructor(private val repository: NewsListRepository) {

    operator fun invoke(category: Category) = repository.setCategory(category)
}