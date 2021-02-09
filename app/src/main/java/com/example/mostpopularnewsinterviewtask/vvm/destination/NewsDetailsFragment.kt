package com.example.mostpopularnewsinterviewtask.vvm.destination

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.mostpopularnewsinterviewtask.R
import kotlinx.android.synthetic.main.fragment_news_details.*

class NewsDetailsFragment : Fragment() {
    private val args: NewsDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_news_details, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        args.newsItemToDisplay?.apply {
            f_news_details_title_tv.text = this.title
            f_news_details_author_tv.text = this.byline
            f_news_details_publish_date_tv.text = "Publish date: ${this.published_date}"
            f_news_details_desc_tv.text = "Abstract: ${this.abstract}"
            f_news_details_article_link_tv.text = "Go to article: ${this.url}"
            f_news_details_source_tv.text = "Source: ${this.source}"
        }
    }
}