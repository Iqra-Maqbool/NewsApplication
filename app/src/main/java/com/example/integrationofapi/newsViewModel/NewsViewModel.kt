package com.example.integrationofapi.newsViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.integrationofapi.apiServices.NewsApiService
import com.example.integrationofapi.data.Article
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {

    private val _newsList = MutableLiveData<List<Article>>()
    val newsList: LiveData<List<Article>> = _newsList
    private val newsApi = NewsApiService.create()

    fun fetchHeadlines(country: String, apiKey: String) {
        viewModelScope.launch {
            try {
                val response = newsApi.getTopHeadlines(country, apiKey)
                if (response.status == "ok") {
                    _newsList.postValue(response.articles)
                } else {
                    println("API call failed: ${response.status}")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}




