package com.example.mostpopularnewsinterviewtask.vvm.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mostpopularnewsinterviewtask.R
import com.example.mostpopularnewsinterviewtask.model.NewsItem
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.interfaces.DraweeController
import com.facebook.imagepipeline.request.ImageRequest
import kotlinx.android.synthetic.main.card_single_news_item.view.*


class NewsAdapter(
    private val observer: SelectionPropagator,
    private var newsList: ArrayList<NewsItem> = ArrayList()
) : RecyclerView.Adapter<NewsAdapter.AwarenessHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AwarenessHolder = AwarenessHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.card_single_news_item,
            parent,
            false
        )
    )

    override fun getItemCount(): Int {
        return newsList.size
    }

    class AwarenessHolder(v: View) : RecyclerView.ViewHolder(v) {
        var title = v.card_news_item_title
        var image = v.card_news_item_image
        var goToDetails = v.card_news_item_go_to_details
        var byLine = v.card_news_item_author
        var date= v.card_news_item_publish_date

        fun bindData(newsItem: NewsItem, observer: SelectionPropagator) {
            newsItem.let { item ->
                title.text = item.title
                byLine.text = item.byline
                date.text = item.published_date
                try {
                    when(item.mediaList[0].type.contains("image",ignoreCase = true)){
                        true -> image.setImageURI(Uri.parse(item.mediaList[0].mediaMetadata[0].url ?: ""))
                    }
                }catch (ex:IndexOutOfBoundsException){

                }

                goToDetails.setOnClickListener {
                    observer.propagateSelection(item)
                }
            }


        }
    }

    override fun onBindViewHolder(holder: AwarenessHolder, position: Int) {
        holder.bindData(newsList[position], observer)
    }


    fun clear() {
        newsList = ArrayList()
    }
    fun changeNewsList(updatedList: ArrayList<NewsItem>) {
        newsList = ArrayList(updatedList)
        notifyDataSetChanged()
    }

    interface SelectionPropagator {
        fun propagateSelection (newsItem: NewsItem)
    }
}