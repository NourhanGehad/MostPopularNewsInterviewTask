package com.example.mostpopularnewsinterviewtask

import android.os.Build
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mostpopularnewsinterviewtask.model.NewsItem
//import androidx.test.runner.AndroidJUnit4;
import com.example.mostpopularnewsinterviewtask.vvm.vm.NewsVM
import org.hamcrest.core.IsNot.not
import org.hamcrest.core.IsNull.nullValue
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class NewsVMUnitTest {
    // Subject under test
    private lateinit var vm: NewsVM

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {
        vm = NewsVM()
        vm.newsListMutableLiveData = MutableLiveData<ArrayList<NewsItem>>()
    }
    @Test
    fun getNews() {
        vm.getMostPopularNews()
        var value = vm.newsListMutableLiveData.getOrAwaitValue()

        assertThat(value, not(nullValue()))
    }
}

