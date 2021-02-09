package com.example.mostpopularnewsinterviewtask.vvm.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mostpopularnewsinterviewtask.data.Client
import com.example.mostpopularnewsinterviewtask.data.Const
import com.example.mostpopularnewsinterviewtask.data.Services
import com.example.mostpopularnewsinterviewtask.model.NewsItem
import com.example.mostpopularnewsinterviewtask.model.Response
import com.google.gson.JsonObject
import io.reactivex.Observable
import io.reactivex.android.BuildConfig
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*

class NewsVM : ViewModel() {
    private val services = Client.retrofitClient.create(Services::class.java)
    var newsListMutableLiveData: MutableLiveData<ArrayList<NewsItem>> =
        MutableLiveData<ArrayList<NewsItem>>()
    var compositeDisposable: CompositeDisposable? = null

    fun getMostPopularNews(section: String = "viewed", period: Int = 1) {
        val observable: Observable<Response> = services.getMostPopularNews(section, period, Const.API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        compositeDisposable = CompositeDisposable()
        compositeDisposable!!.add(
            observable.subscribe(
                { o: Response? ->
                    Log.d("herexx","status ${o?.status}")

                    newsListMutableLiveData.setValue(o?.newsList)
                }
            ) { e: Throwable ->
                Log.d(
                    "PVMError",
                    e.message!!
                )
            }
        )

    }
}