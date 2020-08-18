package com.example.newsapp.network

import com.example.newsapp.database.DatabaseArticle
import com.example.newsapp.domain.Article
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArticleResponse(
    val totalResults:Int,
    val status:String,
    val articles: List<NetworkArticle>
)
@JsonClass(generateAdapter = true)
@Json(name="article")
data class NetworkArticle (
    val title:String,
    var author:String?="",
    val urlToImage:String?="",
    val description:String?="",
    val publishedAt:String,
    val content:String?=""
)
data class Source(val id:Int,
                    val name:String)

fun ArticleResponse.asDomainModel():List<Article>{
    return articles.map {
        Article(
            title=it.title,
            author=it.author,
            description=it.description,
            publishedAt = it.publishedAt,
            urlToImage = it.urlToImage,
            content = it.content
        )
    }
}


fun ArticleResponse.asDatabaseModel():Array<DatabaseArticle>{
    return articles.map {
        DatabaseArticle(
            title=it.title,
            author=it.author,
            description=it.description,
            publishedAt = it.publishedAt,
            urlToImage = it.urlToImage,
            content = it.content
        )
    }.toTypedArray()
}

