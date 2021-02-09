package com.example.mostpopularnewsinterviewtask.data

import com.example.mostpopularnewsinterviewtask.model.Response
import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Services {
    @GET("mostpopular/v2/{section}/{period}.json")
    fun getMostPopularNews(
        @Path("section") section: String = "viewed",
        @Path("period") period: Int = 1,
        @Query("api-key") apiKey: String?
    ): Observable<Response>

}