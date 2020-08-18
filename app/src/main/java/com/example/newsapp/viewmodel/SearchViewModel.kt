package com.example.newsapp.viewmodel

import androidx.lifecycle.*
import com.example.newsapp.BuildConfig
import com.example.newsapp.domain.Article
import com.example.newsapp.network.ArticleApi
import com.example.newsapp.network.asDomainModel
import kotlinx.coroutines.launch
import java.io.IOException

class SearchViewModel : ViewModel() {
    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>>
        get() = _articles

    private val _navigateToDetail = MutableLiveData<Article>()
    val navigateToDetail:LiveData<Article>
    get() = _navigateToDetail


    fun onSearch(query:String){
        viewModelScope.launch  {
            try {
                val response = ArticleApi.retrofitService.searchArticles(BuildConfig.APIKEY,query).await()
                _articles.value = response.asDomainModel()
            }catch (e: IOException){

            }
        }
    }

    fun displayArticleDetails(article:Article){
        _navigateToDetail.value = article
    }
    fun displayArticleDetailsComplete(){
        _navigateToDetail.value = null
    }

}
