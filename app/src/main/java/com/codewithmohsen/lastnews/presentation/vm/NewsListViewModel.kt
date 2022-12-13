package com.codewithmohsen.lastnews.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewithmohsen.domain.models.Category
import com.codewithmohsen.domain.useCase.FetchMoreNewsUseCase
import com.codewithmohsen.domain.useCase.RefreshUseCase
import com.codewithmohsen.domain.useCase.SetCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val fetchMoreNewsUseCase: com.codewithmohsen.domain.useCase.FetchMoreNewsUseCase,
    private val refreshUseCase: com.codewithmohsen.domain.useCase.RefreshUseCase,
    private val setCategoryUseCase: com.codewithmohsen.domain.useCase.SetCategoryUseCase
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

    fun fetchNews(category: com.codewithmohsen.domain.models.Category) {
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

    fun getNewsAsFlow() = fetchMoreNewsUseCase.news

    fun cancel() {
        viewModelScope.cancel()
        Timber.d("viewModel cancel")
    }

}