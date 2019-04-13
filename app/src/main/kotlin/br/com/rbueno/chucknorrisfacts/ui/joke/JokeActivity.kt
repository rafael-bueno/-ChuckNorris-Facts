package br.com.rbueno.chucknorrisfacts.ui.joke

import android.os.Bundle
import android.text.util.Linkify
import android.widget.ImageView
import android.widget.TextView
import android.widget.ViewFlipper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import br.com.rbueno.chucknorrisfacts.R
import br.com.rbueno.chucknorrisfacts.model.Joke
import br.com.rbueno.chucknorrisfacts.ui.home.EXTRA_CATEGORY
import br.com.rbueno.chucknorrisfacts.util.ImageLoader
import br.com.rbueno.chucknorrisfacts.util.observe
import br.com.rbueno.chucknorrisfacts.util.toast
import br.com.rbueno.chucknorrisfacts.viewmodel.JokeViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject


private const val FLIPPER_CONTENT_POSITION = 0
private const val FLIPPER_LOADING_POSITION = 1

class JokeActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: JokeViewModel.JokeViewModelFactory

    private val viewModel by lazy { ViewModelProviders.of(this, factory).get(JokeViewModel::class.java) }
    private val imageJoke by lazy { findViewById<ImageView>(R.id.image_joke) }
    private val textJoke by lazy { findViewById<TextView>(R.id.text_joke) }
    private val textLink by lazy { findViewById<TextView>(R.id.text_link_joke) }
    private val flipper by lazy { findViewById<ViewFlipper>(R.id.flipper_joke) }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_joke)
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.apply {
            errorLiveData.observe(this@JokeActivity) {
                toast(R.string.error_default_message)
            }

            loadingLiveData.observe(this@JokeActivity) {
                flipper.displayedChild = if (it)
                    FLIPPER_LOADING_POSITION
                else
                    FLIPPER_CONTENT_POSITION
            }

            jokeLiveData.observe(this@JokeActivity) {
                bindScreen(it)
            }

        }.getJokeByCategory(intent.getStringExtra(EXTRA_CATEGORY))
    }

    private fun bindScreen(joke: Joke) {
        ImageLoader.loadImage(joke.iconUrl, imageJoke)
        textJoke.text = joke.value
        textLink.text = joke.url
        Linkify.addLinks(textLink, Linkify.WEB_URLS)
    }
}