package com.codewithmohsen.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewithmohsen.domain.Resource
import com.codewithmohsen.domain.models.Article
import com.codewithmohsen.domain.models.Category
import com.codewithmohsen.presentation.useCase.FetchMoreNewsUseCase
import com.codewithmohsen.presentation.useCase.RefreshUseCase
import com.codewithmohsen.presentation.useCase.SetCategoryUseCase
import com.codewithmohsen.presentation.mappers.map
import com.codewithmohsen.presentation.uiModels.UiArticle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val fetchMoreNewsUseCase: FetchMoreNewsUseCase,
    private val refreshUseCase: RefreshUseCase,
    private val setCategoryUseCase: SetCategoryUseCase
) : ViewModel() {

    private val selectedCategory = MutableLiveData<Int>()
    fun getSelectedCategory(): LiveData<Int> = selectedCategory
    fun setSelectedCategory(category: Int) {
        selectedCategory.value = category
    }

    fun fetchMoreNews() {
        viewModelScope.launch {
            fetchMoreNewsUseCase.fetchMoreNews()
        }
    }

    fun fetchNews(category: Category) {
        setCategoryUseCase(category)
        viewModelScope.launch {
            refreshUseCase()
        }
    }

    fun refresh() {
        viewModelScope.launch {
            refreshUseCase()
        }
    }

    fun getNewsAsFlow(): Flow<Resource<List<UiArticle>>> =
        fetchMoreNewsUseCase.news

    fun cancel() {
        viewModelScope.cancel()
        Timber.d("viewModel cancel")
    }

}