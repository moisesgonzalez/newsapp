package com.example.newsapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.newsapp.database.ArticlesDatabase
import com.example.newsapp.database.asDomainModel
import com.example.newsapp.domain.Article
import com.example.newsapp.network.ArticleApi
import com.example.newsapp.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ArticlesRepository(private val database: ArticlesDatabase, private val apiKey:String) {


    val articles:LiveData<List<Article>> = Transformations.map(database.articleDao.getArticles()){
        it.asDomainModel()
    }

    suspend fun refreshArticles(){
        withContext(Dispatchers.IO){
            val response = ArticleApi.retrofitService.getHeadlines(apiKey,"us").await()
            database.articleDao.insertAll(*response.asDatabaseModel())
        }
    }
}

