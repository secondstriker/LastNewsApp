package com.codewithmohsen.lastnews.clean.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.codewithmohsen.lastnews.clean.presentation.uiModels.UiArticle
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val savedStateHandle: SavedStateHandle): ViewModel() {

    val uiArticle: LiveData<UiArticle> =
        savedStateHandle.getLiveData(SAVED_STATE_KEY)

    fun setArticle(uiArticle: UiArticle) {
        savedStateHandle[SAVED_STATE_KEY] = uiArticle
    }

    companion object {

        private const val SAVED_STATE_KEY = "saved_state_key"
    }
}