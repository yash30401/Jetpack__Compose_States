package com.yash.jetpackcomposestates.repository

import com.yash.jetpackcomposestates.api.TweetsApi
import com.yash.jetpackcomposestates.models.TweetListItem
import com.yash.jetpackcomposestates.networking.NetworkResult
import com.yash.jetpackcomposestates.utils.await
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TweetRepository @Inject constructor(
    private val tweetsApi: TweetsApi
) {

    suspend fun getCategories(): Flow<List<String>> {
        return flow {
                val response = tweetsApi.getCategories().await()
                emit(response)
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTweets(category:String):Flow<List<TweetListItem>>{
        return flow {
                val response = tweetsApi.getTweets("tweets[?(@.category==\"$category\")]").await()
                emit(response)
        }.flowOn(Dispatchers.IO)
    }

}