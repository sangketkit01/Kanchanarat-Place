package com.src.kanchanaratplace.navigation

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun BottomBar(navController : NavHostController){
    val navigationItems = listOf(Screen.First,Screen.Apartment,Screen.Chat,Screen.Profile,Screen.Notification)
    var selectedScreen by remember { mutableIntStateOf(0) }

    NavigationBar (
        containerColor = Color(94, 144, 255, 255),

    ){
        navigationItems.forEachIndexed{ index , screen ->
            NavigationBarItem(
                icon = {
                    screen.icon?.let {
                        Icon(
                            imageVector = it,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                },
                label = {
                    Text(
                        text = screen.name,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    ) },
                selected = (selectedScreen == index),
                onClick = {
                    selectedScreen = index
                    navController.navigate(screen.route)
                },
            )
        }
    }
}