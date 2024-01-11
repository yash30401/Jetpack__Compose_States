package com.yash.jetpackcomposestates.api

import com.yash.jetpackcomposestates.models.TweetListItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface TweetsApi {

    @GET("/v3/b/659ed9bb1f5677401f1a5db8?meta=false")
    suspend fun getTweets(@Header("X-JSON-Path") category:String):Response<List<TweetListItem>>

    @GET("/v3/b/659ed9bb1f5677401f1a5db8?meta=false")
    @Headers("X-JSON-Path : tweets.category")
    suspend fun getCategories():Response<List<String>>
}