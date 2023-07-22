package com.example.navigation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
//navigation import rememberNavController()
import androidx.navigation.compose.rememberNavController
import com.example.navigation.ui.theme.NavigationTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.navigation.ui.theme.DetailScreen
import com.example.navigation.ui.theme.FeedScreen
import com.example.navigation.ui.theme.ListScreen
import com.example.navigation.ui.theme.ProfileScreen

//refactoring the nav contorllers

sealed class Destination(val route: String){
    object Home: Destination(route = "Home")
    object Feed: Destination(route = "Feed")
    object Profile: Destination(route = "Profile")
    object List: Destination(route = "List")
    object Detail: Destination(route = "detail/{userId}"){
        fun createRoute(userId: Int) = "detail/$userId"
    }

}

//data
data class User(
    val id: Int,
    val name: String,
    val surname: String
) {
    companion object{
        private val users = listOf(
            User(0, "John", "Smith"),
            User(1, "Susan", "Smith"),
            User(2, "David", "Brown"),
            User(3, "Margaret", "Brown"),
            User(4, "Michael", "Jones"),
            User(5, "Patricia", "Jones"),
            User(6, "Andrew", "Williams"),
            User(7, "Sarah", "Williams"),
            User(8, "Robert", "Perry"),
            User(9, "Mary", "Perry"),
        )

        fun getTestList() = users

        fun getUser(id: Int): User? {
            for (i in users.indices) {
                if (users[i].id == id) return users[i]
            }
            return null
        }
    }
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
    val ctx = LocalContext.current;

    NavHost(navController = navController, startDestination="home"){
        composable(Destination.Home.route){ HomeScreen(navController) }
        composable(Destination.Feed.route){ FeedScreen(navController) }
        composable(Destination.Profile.route){ ProfileScreen(navController) }
        composable(Destination.List.route){ ListScreen(navController) }
        composable(Destination.Detail.route){ navBackStackEntry ->
            val userId = navBackStackEntry.arguments?.getString("userId")
            if (userId == null )
                Toast.makeText(ctx, "UserId is required",
                Toast. LENGTH_SHORT).show()
            else
                DetailScreen(navController = navController, userId = userId.toInt() )
            }
        }
    }

