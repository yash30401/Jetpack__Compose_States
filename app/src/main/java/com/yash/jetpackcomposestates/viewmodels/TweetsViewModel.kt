package com.yash.jetpackcomposestates.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yash.jetpackcomposestates.models.TweetListItem
import com.yash.jetpackcomposestates.networking.NetworkResult
import com.yash.jetpackcomposestates.repository.TweetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TweetsViewModel @Inject constructor(
    private val tweetRepository: TweetRepository
) : ViewModel() {

    private val _categories = MutableStateFlow<List<String>?>(null)
    val categories: StateFlow<List<String>?> = _categories
    private val _tweetListItem = MutableStateFlow<List<TweetListItem>?>(null)
    val tweetListItem:StateFlow<List<TweetListItem>?> = _tweetListItem

    init {
        getCategories()
    }
    fun getCategories() = viewModelScope.launch {
            val result = tweetRepository.getCategories()
            result.collect{
               _categories.value = it
           }

    }

    fun getTweets(category:String) = viewModelScope.launch {
            val result = tweetRepository.getTweets(category)
            result.collect {
               _tweetListItem.value = it
            }
    }
}