package com.codewithmohsen.lastnews.clean.domain.useCase

import com.codewithmohsen.lastnews.clean.domain.models.Category
import com.codewithmohsen.lastnews.clean.domain.repository.NewsListRepository
import javax.inject.Inject

class SetCategoryUseCase @Inject constructor(private val repository: NewsListRepository) {

    operator fun invoke(category: Category) = repository.setCategory(category)
}