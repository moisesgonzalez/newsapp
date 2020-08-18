package com.example.newsapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsapp.domain.Article

@Entity
data class DatabaseArticle constructor(
    @PrimaryKey
    val title:String,
    var author:String?="",
    val urlToImage:String?="",
    val description:String?="",
    val publishedAt:String,
    val content:String?=""
)
fun List<DatabaseArticle>.asDomainModel(): List<Article> {
    return map {
        Article (
            title = it.title,
            author = it.author,
            urlToImage = it.urlToImage,
            description = it.description,
            publishedAt = it.publishedAt,
            content = it.content)
    }
}