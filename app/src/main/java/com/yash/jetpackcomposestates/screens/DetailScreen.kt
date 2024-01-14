package com.yash.jetpackcomposestates.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.yash.jetpackcomposestates.viewmodels.TweetsViewModel


@Composable
fun DetailScreen() {
    val tweetsViewModel:TweetsViewModel = viewModel()
    tweetsViewModel.getTweets("android")
    val tweets = tweetsViewModel.tweetListItem.collectAsState()

    LazyColumn(content = {
        if(tweets.value!=null){
            items(tweets.value!!){
               TweetListItem(tweet = it.text)
            }
        }
    })

}

@Composable
fun TweetListItem(tweet:String) {
    Card(modifier =
    Modifier
        .fillMaxWidth()
        .padding(16.dp),
        border = BorderStroke(1.dp, Color(0xFFCCCCCC))
    ) {
        Text(text = tweet,
            Modifier.padding(16.dp), style = MaterialTheme.typography.bodyLarge)
    }
}