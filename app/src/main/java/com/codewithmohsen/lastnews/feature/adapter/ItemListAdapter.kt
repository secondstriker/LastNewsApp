package com.codewithmohsen.lastnews.feature.adapter

import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.codewithmohsen.lastnews.R
import com.codewithmohsen.lastnews.databinding.NewsItemBinding
import com.codewithmohsen.lastnews.domain.di.DefaultDispatcher
import com.codewithmohsen.lastnews.presentation.uiModels.UiArticle
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * A RecyclerView adapter for [Item List] class.
 */
class ItemListAdapter(
    @DefaultDispatcher defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,
    private val itemClickCallback: ((UiArticle) -> Unit)?
) : DataBoundListAdapter<UiArticle, NewsItemBinding>(
    defaultDispatcher,
    diffCallback = object : DiffUtil.ItemCallback<UiArticle>() {
        override fun areItemsTheSame(oldItem: UiArticle, newItem: UiArticle): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: UiArticle, newItem: UiArticle): Boolean {
            return oldItem == newItem
        }
    }
) {

    override fun createBinding(parent: ViewGroup): NewsItemBinding {

        val binding = DataBindingUtil.inflate<NewsItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.news_item,
            parent,
            false)
        binding.root.setOnClickListener { _ ->
            binding.item.let {
                if(it != null) itemClickCallback?.invoke(it)
            }
        }

        return binding
    }

    override fun bind(binding: NewsItemBinding, item: UiArticle) {
        binding.item = item
    }
}
