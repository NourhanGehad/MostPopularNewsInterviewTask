package com.example.mostpopularnewsinterviewtask.model

import com.google.gson.annotations.SerializedName

data class Response(

    @SerializedName("status") val status: String,
    @SerializedName("copyright") val copyright: String,
    @SerializedName("num_results") val totalResults: Int,
    @SerializedName("results") val newsList: ArrayList<NewsItem>
)
