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

    suspend fun getCategories(): Flow<NetworkResult<List<String>>> {
        return flow {
            try {
                val response = tweetsApi.getCategories().await()
                emit(NetworkResult.Success(response))
            } catch (e: Exception) {
                emit(NetworkResult.Error(e.message))
            }
        }.catch {
            emit(NetworkResult.Error(it.message))
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTweets(category:String):Flow<NetworkResult<List<TweetListItem>>>{
        return flow {
            try {
                val response = tweetsApi.getTweets(category).await()
                emit(NetworkResult.Success(response))
            }catch (e:Exception){
                emit(NetworkResult.Error(e.message))
            }
        }.catch {
            emit(NetworkResult.Error(it.message))
        }.flowOn(Dispatchers.IO)
    }

}