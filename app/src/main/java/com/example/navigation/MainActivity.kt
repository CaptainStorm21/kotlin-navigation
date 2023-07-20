package com.example.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
//navigation import rememberNavController()
import androidx.navigation.compose.rememberNavController
import com.example.navigation.ui.theme.NavigationTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.navigation.ui.theme.FeedScreen
import com.example.navigation.ui.theme.ProfileScreen

//refactoring the nav contorllers

sealed class Destination(val route: String){
    object Home: Destination(route = "Home")
    object Feed: Destination(route = "Feed")
    object Profile: Destination(route = "Profile")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavigationAppHost(navController = navController)
                }
            }
        }
    }
}

@Composable()
fun NavigationAppHost(navController: NavHostController){
    NavHost(navController = navController, startDestination="home"){
        composable(Destination.Home.route){ HomeScreen(navController) }
        composable(Destination.Feed.route){ FeedScreen(navController) }
        composable(Destination.Profile.route){ ProfileScreen(navController) }
    }
}
