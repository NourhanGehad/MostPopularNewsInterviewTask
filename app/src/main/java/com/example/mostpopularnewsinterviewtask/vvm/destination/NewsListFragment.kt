package com.example.mostpopularnewsinterviewtask.vvm.destination

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mostpopularnewsinterviewtask.R
import com.example.mostpopularnewsinterviewtask.data.Client
import com.example.mostpopularnewsinterviewtask.model.NewsItem
import com.example.mostpopularnewsinterviewtask.vvm.adapter.NewsAdapter
import com.example.mostpopularnewsinterviewtask.vvm.vm.NewsVM
import kotlinx.android.synthetic.main.fragment_news_list.*

class NewsListFragment : Fragment() {
    private val vm: NewsVM by lazy {
        ViewModelProviders.of(this).get(NewsVM::class.java)
    }
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_news_list, container, false)

    override fun onStart() {
        super.onStart()
        Client.reinstantiateClient()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerview()

        vm.getMostPopularNews()
        vm.newsListMutableLiveData.observe(activity!!, Observer { recievedNewsList->
            newsAdapter.changeNewsList(recievedNewsList)
            Log.d("here","recievedNewsList size"+ recievedNewsList.size)
        })
    }

    private fun initRecyclerview(){
        newsAdapter = NewsAdapter(
            observer = object: NewsAdapter.SelectionPropagator{
                override fun propagateSelection(newsItem: NewsItem) {
                    //Navigate
                    Log.d("ClickedOnItem",newsItem.title)
                    findNavController().navigate(NewsListFragmentDirections.actionNewsListFragmentToNewsDetailsFragment().setNewsItemToDisplay(newsItem))
                }
            }
        )
        val linearLayoutManager = LinearLayoutManager(context)
        f_news_list_news_rv.apply {
            adapter = newsAdapter
            layoutManager = linearLayoutManager
        }

    }
}