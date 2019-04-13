package br.com.rbueno.chucknorrisfacts

import androidx.lifecycle.Observer
import br.com.rbueno.chucknorrisfacts.repository.HomeRepository
import br.com.rbueno.chucknorrisfacts.viewmodel.HomeViewModel
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest : BaseViewModelTest<HomeViewModel>() {

    @Mock
    private lateinit var repository: HomeRepository
    @Mock
    private lateinit var categoriesObserver: Observer<List<String>>

    @Before
    override fun setup() {
        viewModel = HomeViewModel(repository)
        viewModel.categoriesLiveData.observeForever(categoriesObserver)
        super.setup()
    }

    @After
    override fun teardown() {
        viewModel.categoriesLiveData.removeObserver(categoriesObserver)
        super.teardown()
    }

    @Test
    fun shouldPostCategoriesValue_WhenApiReturns() {
        //given
        `when`(repository.getCategories()).thenReturn(getMockedCategoryList())
        //when
        viewModel.loadCategories()
        //then
        verify(categoriesObserver).onChanged(viewModel.categoriesLiveData.value)
    }

    private fun getMockedCategoryList() = Single.just(listOf<String>())
}