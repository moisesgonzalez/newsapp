package com.example.newsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.domain.Article

class DetailArticleViewModelFactory(
    private val article: Article
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailArticleViewModel::class.java)) {
            return DetailArticleViewModel(article) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
