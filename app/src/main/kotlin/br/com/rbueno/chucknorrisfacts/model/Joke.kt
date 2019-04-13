package br.com.rbueno.chucknorrisfacts.model

import com.google.gson.annotations.SerializedName

data class Joke(
    @SerializedName("category") val category: List<String>,
    @SerializedName("icon_url") val iconUrl: String,
    @SerializedName("id") val id: String,
    @SerializedName("url") val url: String,
    @SerializedName("value") val value: String
)