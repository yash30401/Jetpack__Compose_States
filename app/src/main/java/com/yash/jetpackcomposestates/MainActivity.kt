package com.yash.jetpackcomposestates

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.yash.jetpackcomposestates.api.TweetsApi
import com.yash.jetpackcomposestates.screens.CategoryScreen
import com.yash.jetpackcomposestates.screens.DetailScreen
import com.yash.jetpackcomposestates.ui.theme.JetpackComposeStatesTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var tweetsApi: TweetsApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeStatesTheme {
                // A surface container using the 'background' color from the theme
                App()
            }
        }
    }

}

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Category"){
        composable(route = "Category"){
            CategoryScreen(){
                navController.navigate("Detail/${it}")
            }
        }
        composable(route = "Detail/{category}",
            arguments = listOf(navArgument("category"){
                type = NavType.StringType
            })
        ){
            val category = it.arguments!!.getString("category")
            DetailScreen(category)
        }
    }
}



