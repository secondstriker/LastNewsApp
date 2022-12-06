package com.codewithmohsen.lastnews.feature.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.codewithmohsen.lastnews.R
import com.codewithmohsen.lastnews.feature.adapter.ItemListAdapter
import com.codewithmohsen.lastnews.databinding.ActivityNewsListBinding
import com.codewithmohsen.lastnews.domain.models.Category
import com.codewithmohsen.lastnews.domain.Status
import com.codewithmohsen.lastnews.feature.view.custom.EndlessRecyclerOnScrollListener
import com.codewithmohsen.lastnews.presentation.vm.NewsListViewModel
import kotlinx.coroutines.flow.collect
import timber.log.Timber


@AndroidEntryPoint
class NewsListActivity : AppCompatActivity() {

    private val viewModel: NewsListViewModel by viewModels()
    private var adapter: ItemListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityNewsListBinding = DataBindingUtil.setContentView(this, R.layout.activity_news_list)
        binding.lifecycleOwner = this

        adapter = ItemListAdapter { item ->
            DetailsActivity.startActivity(this, item)
        }

        binding.itemList.layoutManager = GridLayoutManager(this, 2)
        binding.itemList.adapter = adapter

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getNewsAsFlow().collect { resource ->
                    adapter!!.submitList(resource.data)
                    binding.status = resource.status
                    if(binding.swipeRefresh.isRefreshing)
                        binding.swipeRefresh.isRefreshing = Status.LOADING == resource.status
                            || Status.LONG_LOADING == resource.status

                    if(resource.status == Status.ERROR)
                        Toast.makeText(this@NewsListActivity, resource.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.fetchNews(category = Category.general)

        binding.itemList.addOnScrollListener(object :
            EndlessRecyclerOnScrollListener(binding.itemList.layoutManager!!,0){
            override fun onLoadMore() {
                Timber.d("fetch more")
                viewModel.fetchMoreNews()
            }
        })

        binding.cancelContainer.cancelButton.setOnClickListener {
            viewModel.cancel()
        }

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_filter -> {
                SelectCategoryDialogFragment.newInstance().show(supportFragmentManager, this::class.java.name)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        adapter = null
        super.onDestroy()
    }
}