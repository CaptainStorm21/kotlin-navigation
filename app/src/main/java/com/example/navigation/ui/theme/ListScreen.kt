package com.example.navigation.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.navigation.Destination
import com.example.navigation.User


@Composable
fun ListScreen(navController: NavController){
    val users = User.getTestList()

    LazyColumn{
        items(users){
            Column(modifier = Modifier
                .clickable {
                    val route = Destination.Detail.createRoute(it.id)
                    navController.navigate(route)
                }
                .padding(4.dp)
                .clip(RoundedCornerShape(8.dp))
                .fillMaxWidth()
                .background(Color(color = 0xffceded5))
                .padding(8.dp)
            ){
                Text(text = it.name, fontWeight = FontWeight.Bold, color=Color(0xFF654321))
                Text(text = it.surname, fontWeight = FontWeight.Bold)
            }
        }
    }
}