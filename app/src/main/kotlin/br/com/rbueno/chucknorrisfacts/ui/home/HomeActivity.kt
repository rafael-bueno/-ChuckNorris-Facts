package br.com.rbueno.chucknorrisfacts.ui.home

import android.content.Intent
import android.os.Bundle
import android.widget.ViewFlipper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.rbueno.chucknorrisfacts.R
import br.com.rbueno.chucknorrisfacts.ui.joke.JokeActivity
import br.com.rbueno.chucknorrisfacts.util.observe
import br.com.rbueno.chucknorrisfacts.util.toast
import br.com.rbueno.chucknorrisfacts.viewmodel.HomeViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

const val EXTRA_CATEGORY = "CATEGORY"
private const val FLIPPER_RECYCLER_POSITION = 0
private const val FLIPPER_LOADING_POSITION = 1

class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: HomeViewModel.HomeViewModelFactory

    private val viewModel by lazy { ViewModelProviders.of(this, factory).get(HomeViewModel::class.java) }
    private val recyclerCategories by lazy { findViewById<RecyclerView>(R.id.recycler_categories) }
    private val flipper by lazy { findViewById<ViewFlipper>(R.id.flipper_home) }
    private val adapter by lazy { HomeAdapter(mutableListOf()) { navigateToJoke(it) } }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initViewModel()
        bindRecyclerView()
    }

    private fun initViewModel() {
        viewModel.apply {
            errorLiveData.observe(this@HomeActivity) {
                toast(R.string.error_default_message)
            }

            loadingLiveData.observe(this@HomeActivity) {
                flipper.displayedChild = if (it)
                    FLIPPER_LOADING_POSITION
                else
                    FLIPPER_RECYCLER_POSITION

            }

            categoriesLiveData.observe(this@HomeActivity) {
                addItemsToAdapter(it)
            }

        }.loadCategories()
    }

    private fun addItemsToAdapter(categories: List<String>) {
        adapter.setItems(categories)
    }

    private fun bindRecyclerView() {
        with(recyclerCategories) {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            addItemDecoration(DividerItemDecoration(this@HomeActivity, DividerItemDecoration.VERTICAL))
            adapter = this@HomeActivity.adapter
        }
    }

    private fun navigateToJoke(category: String) {
        startActivity(Intent(this, JokeActivity::class.java).apply {
            putExtra(EXTRA_CATEGORY, category)
        })
    }
}