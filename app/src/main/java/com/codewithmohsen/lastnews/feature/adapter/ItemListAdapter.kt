package com.codewithmohsen.lastnews.feature.adapter

import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.codewithmohsen.lastnews.R
import com.codewithmohsen.lastnews.databinding.NewsItemBinding
import com.codewithmohsen.presentation.UiArticle
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * A RecyclerView adapter for [Item List] class.
 */
class ItemListAdapter(
    @com.codewithmohsen.common.di.DefaultDispatcher defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,
    private val itemClickCallback: ((com.codewithmohsen.presentation.UiArticle) -> Unit)?
) : DataBoundListAdapter<com.codewithmohsen.presentation.UiArticle, NewsItemBinding>(
    defaultDispatcher,
    diffCallback = object : DiffUtil.ItemCallback<com.codewithmohsen.presentation.UiArticle>() {
        override fun areItemsTheSame(oldItem: com.codewithmohsen.presentation.UiArticle, newItem: com.codewithmohsen.presentation.UiArticle): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: com.codewithmohsen.presentation.UiArticle, newItem: com.codewithmohsen.presentation.UiArticle): Boolean {
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

    override fun bind(binding: NewsItemBinding, item: com.codewithmohsen.presentation.UiArticle) {
        binding.item = item
    }
}
