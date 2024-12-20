package com.example.pr5mybd.api



import com.example.pr5mybd.models.NewsResponse
import com.example.pr5mybd.ui.Constants.Companion.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response

interface NewsApi {
    @GET("v2/top-headlines")
    suspend fun getHeadlines(
        @Query("country")
        countryCode: String = "us",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResponse>
}