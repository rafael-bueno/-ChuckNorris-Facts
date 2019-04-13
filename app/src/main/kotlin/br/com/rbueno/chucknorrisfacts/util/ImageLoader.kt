package br.com.rbueno.chucknorrisfacts.util

import android.widget.ImageView
import br.com.rbueno.chucknorrisfacts.R
import com.bumptech.glide.Glide

class ImageLoader {
    companion object {
        fun loadImage(
            imageUrl: String,
            imageView: ImageView,
            placeholderRes: Int = R.mipmap.ic_launcher,
            imageErrorRes: Int = R.mipmap.ic_launcher
        ) {
            Glide.with(imageView.context)
                .load(imageUrl)
                .placeholder(placeholderRes)
                .error(imageErrorRes)
                .into(imageView)
        }
    }
}