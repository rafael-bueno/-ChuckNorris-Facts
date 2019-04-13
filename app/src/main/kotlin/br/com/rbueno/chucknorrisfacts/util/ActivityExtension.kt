package br.com.rbueno.chucknorrisfacts.util

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.toast(messageId: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, messageId, duration).show()
}