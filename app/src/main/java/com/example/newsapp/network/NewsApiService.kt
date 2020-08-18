package com.example.newsapp.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private const val BASE_URL = "https://newsapi.org/v2/"
interface NewsApiService {
    @GET("top-headlines")
    fun getHeadlines(@Query("apiKey") key: String, @Query("country")country: String):
            Deferred<ArticleResponse>
    @GET("everything")
    fun searchArticles(@Query("apiKey") key: String, @Query("q") query: String):
            Deferred<ArticleResponse>
}

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


object ArticleApi{
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
    val retrofitService: NewsApiService = retrofit.create(NewsApiService::class.java)
}