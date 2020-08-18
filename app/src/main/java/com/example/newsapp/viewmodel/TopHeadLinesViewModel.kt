package com.example.newsapp.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.newsapp.repository.ArticlesRepository
import com.example.newsapp.BuildConfig
import com.example.newsapp.database.getDatabase
import com.example.newsapp.domain.Article
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.IOException

class TopHeadLinesViewModel(application: Application) : AndroidViewModel(application) {


    private var currentJob: Job? = null
    private val database = getDatabase(application)
    private val respository = ArticlesRepository(
        database,
        BuildConfig.APIKEY
    )
    val articles = respository.articles

    private val _navigateToDetail = MutableLiveData<Article>()
    val navigateToDetail:LiveData<Article>
    get() = _navigateToDetail
    init{
        viewModelScope.launch {
            try {
            respository.refreshArticles()
            } catch (e: IOException) {
                //_gdgList.value = listOf()
            }
        }

    }
    override fun onCleared() {
        super.onCleared()
        currentJob?.cancel()
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(TopHeadLinesViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return TopHeadLinesViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
    fun displayArticleDetails(article: Article){
        _navigateToDetail.value = article
    }
    fun displayArticleDetailsComplete(){
        _navigateToDetail.value = null
    }


}