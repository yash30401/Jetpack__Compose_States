package com.yash.jetpackcomposestates.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.yash.jetpackcomposestates.R
import com.yash.jetpackcomposestates.networking.NetworkResult
import com.yash.jetpackcomposestates.viewmodels.TweetsViewModel
import java.util.Locale.Category

@Composable
fun CategoryScreen(onClick:(category:String)->Unit) {
    val tweetsViewModel: TweetsViewModel = hiltViewModel()
    var categories = tweetsViewModel.categories.collectAsState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2), contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        if (categories.value != null) {
            items(categories.value?.distinct()!!) {
                CategoryItem(category = it,onClick)
            }
        } else {
            Log.d("DATATEST", categories.toString())
        }

    }
}

@Composable
fun CategoryItem(category: String, onClick:(category:String)->Unit) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .clickable {
                onClick(category)
            }
            .size(160.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, Color(0xFFEEEEEE))
            .paint(
                painter = painterResource(id = R.drawable.layered_waves_haikei),
                contentScale = ContentScale.Crop
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = category,
            fontSize = 24.sp,
            color = Color.White,
            modifier = Modifier.padding(0.dp, 20.dp),
            style = MaterialTheme.typography.headlineLarge
        )
    }
}