package br.com.rbueno.chucknorrisfacts.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observe(owner: LifecycleOwner, func: (T) -> Unit) =
    observe(owner, Observer<T> { it?.let { func(it) } })