package com.example.navigation


import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material.BottomNavigation
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigation.ui.theme.DetailScreen
import com.example.navigation.ui.theme.FeedScreen
import com.example.navigation.ui.theme.ListScreen
import com.example.navigation.ui.theme.NavigationTheme
import com.example.navigation.ui.theme.ProfileScreen
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")

//refactoring the nav contorellers

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
                    //NavigationAppHost(navController = navController)
                    BottomNavTest(navController = navController)
                }
            }
        }
    }
}

@Composable
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




@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavTest(navController: NavHostController){
    val materialBlue100= Color(0xFFf4c2c2)


    Scaffold(bottomBar = {
        BottomNavigation{
            val navBackStackEntry = navController.currentBackStackEntry
            val currentDestination = navBackStackEntry?.destination

            BottomNavigationItem(
                selected = currentDestination?.route == Destination.Home.route,
                onClick = { navController.navigate(Destination.Home.route) },
                icon = { Icon(Icons.Default.Home, contentDescription = null)},
                label = { Text(text = Destination.Home.route)}
            )
            BottomNavigationItem(
                selected = currentDestination?.route == Destination.Feed.route,
                onClick = { navController.navigate(Destination.Feed.route) },
                icon = { Icon(Icons.Default.List, contentDescription = null)},
                label = { Text(text = Destination.Feed.route)}
            )
            BottomNavigationItem(
                selected = currentDestination?.route == Destination.List.route,
                onClick = { navController.navigate(Destination.List.route) },
                icon = { Icon(Icons.Default.List, contentDescription = null)},
                label = { Text(text = Destination.List.route)}
            )
            BottomNavigationItem(
                selected = currentDestination?.route == Destination.Profile.route,
                onClick = { navController.navigate(Destination.Profile.route) },
                icon = { Icon(Icons.Default.Person, contentDescription = null)},
                label = { Text(text = Destination.Profile.route)}
            )
        }
    }) {
        NavHost(navController = navController, startDestination = Destination.Home.route){
            composable(Destination.Home.route){ HomeScreen(navController) }
            composable(Destination.Feed.route){ FeedScreen(navController) }
            composable(Destination.Profile.route){ ProfileScreen(navController) }
            composable(Destination.List.route){ ListScreen(navController) }

        }
    }
}