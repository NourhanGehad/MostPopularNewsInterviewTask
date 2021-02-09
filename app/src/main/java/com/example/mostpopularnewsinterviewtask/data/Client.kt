package com.example.mostpopularnewsinterviewtask.data

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Client {
    private var okHttpClient: OkHttpClient? = null
    private var services: Services? = null
    var retrofitClient = initClient()

    private fun initHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient().newBuilder()
            .connectTimeout(Const.CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(Const.REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(Const.REQUEST_TIMEOUT, TimeUnit.SECONDS)
        httpClient.addInterceptor(Interceptor.invoke {
            val original = it.request()
            val requestBuilder = original.newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
            val request = requestBuilder.build()
            it.proceed(request)
        })
        return httpClient.build()
    }

    private fun initClient(): Retrofit{
        if (okHttpClient == null) {
            reinstantiateClient()
        }
        return Retrofit.Builder()
            .baseUrl(Const.BASE_URL).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
    fun reinstantiateClient() {
        okHttpClient =
            initHttpClient()
        retrofitClient =
            initClient()
    }



}