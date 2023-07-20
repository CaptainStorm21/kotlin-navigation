package com.example.navigation


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun HomeScreen( navController: NavHostController){
    Column(modifier = Modifier.fillMaxSize(),
           horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly){

        Text(text = "home screen", fontSize=30.sp)

        Button(onClick = { navController.navigate(route = Destination.Feed.route)}){
            Text( text = "to feed screen")
        }

        Button(onClick = { navController.navigate(route = Destination.Profile.route)}){
            Text( text = "to profile screen")
        }    }

}