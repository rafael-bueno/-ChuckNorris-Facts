package br.com.rbueno.chucknorrisfacts

import androidx.lifecycle.Observer
import br.com.rbueno.chucknorrisfacts.model.Joke
import br.com.rbueno.chucknorrisfacts.repository.JokeRepository
import br.com.rbueno.chucknorrisfacts.viewmodel.JokeViewModel
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.ArgumentMatchers.*
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class JokeViewModelTest : BaseViewModelTest<JokeViewModel>() {

    @Mock
    private lateinit var repository: JokeRepository
    @Mock
    private lateinit var jokeObserver: Observer<Joke>

    @Before
    override fun setup() {
        viewModel = JokeViewModel(repository)
        viewModel.jokeLiveData.observeForever(jokeObserver)
        super.setup()
    }

    @After
    override fun teardown() {
        viewModel.jokeLiveData.removeObserver(jokeObserver)
        super.teardown()
    }

    @Test
    fun shouldPostJokeValue_WhenApiReturns() {
        //given
        `when`(repository.getJokeByCategory(anyString())).thenReturn(getMockedJoke())
        //when
        viewModel.getJokeByCategory(anyString())
        //then
        verify(jokeObserver).onChanged(viewModel.jokeLiveData.value)
    }

    private fun getMockedJoke() = Single.just(Joke(listOf(), "", "", "", ""))
}