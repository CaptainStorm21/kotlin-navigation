package com.example.navigation.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material3.Button
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.navigation.Destination

@Composable
fun ProfileScreen(navController: NavHostController){
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly){

        Text(text = "profile screen", fontSize=30.sp)

        Button(onClick = {navController.navigate(Destination.Feed.route) {
            popUpTo(Destination.Home.route)
            launchSingleTop=true
        } }) {
            Text(text = "to Feed")
        }
        Button(onClick = {navController.navigate(Destination.List.route){
            popUpTo(Destination.List.route)
        }}) {
            Text(text = "to the List")
        }
        Button(onClick = {navController.popBackStack() }) {
            Text(text = "back")
        }
    }
}