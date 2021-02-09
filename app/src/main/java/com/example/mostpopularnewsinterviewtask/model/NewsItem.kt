package com.example.mostpopularnewsinterviewtask.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NewsItem(	@SerializedName("uri") val uri : String,
                        @SerializedName("url") val url : String,
                        @SerializedName("id") val id : Long,
                        @SerializedName("asset_id") val asset_id : Long,
                        @SerializedName("source") val source : String,
                        @SerializedName("published_date") val published_date : String,
                        @SerializedName("updated") val updated : String,
                        @SerializedName("section") val section : String,
                        @SerializedName("subsection") val subsection : String,
                        @SerializedName("nytdsection") val nytdsection : String,
                        @SerializedName("adx_keywords") val adx_adx_keywordswords : String,
                        @SerializedName("column") val column : String,
                        @SerializedName("byline") val byline : String,
                        @SerializedName("type") val type : String,
                        @SerializedName("title") val title : String,
                        @SerializedName("abstract") val abstract : String,
                        @SerializedName("media") val mediaList : ArrayList<MediaItem>,
                        @SerializedName("eta_id") val eta_id : Int
) : Serializable
