package com.codewithmohsen.lastnews.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewithmohsen.lastnews.domain.models.Category
import com.codewithmohsen.lastnews.domain.useCase.FetchMoreNewsUseCase
import com.codewithmohsen.lastnews.domain.useCase.RefreshUseCase
import com.codewithmohsen.lastnews.domain.useCase.SetCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val fetchMoreNewsUseCase: FetchMoreNewsUseCase,
    private val refreshUseCase: RefreshUseCase,
    private val setCategoryUseCase: SetCategoryUseCase
) : ViewModel() {

    private lateinit var job: Job

    private val selectedCategory = MutableLiveData<Int>()
    fun getSelectedCategory(): LiveData<Int> = selectedCategory
    fun setSelectedCategory(category: Int) {
        selectedCategory.value = category
    }

    fun fetchMoreNews() {
        newJob()
        viewModelScope.launch(job) {
            fetchMoreNewsUseCase.fetchMoreNews()
        }
    }

    fun fetchNews(category: Category) {
        newJob()
        setCategoryUseCase(category)
        viewModelScope.launch(job) {
            refreshUseCase()
        }
    }

    fun refresh() {
        newJob()
        viewModelScope.launch(job) {
            refreshUseCase()
        }
    }

    fun getNewsAsFlow() = fetchMoreNewsUseCase.news

    fun cancel() {
        job.cancel()
        Timber.d("viewModel cancel")
    }

    private fun newJob() {
        if(!this::job.isInitialized)
            job = Job()
        if (job.isActive || job.isCancelled) {
            job.cancel()
            job = Job()
            job.invokeOnCompletion {
                Timber.d("viewModel job completed. is cancelled? ${job.isCancelled}")
            }
        }
    }

}