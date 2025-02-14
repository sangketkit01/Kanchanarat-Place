package com.src.kanchanaratplace.screen.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.src.kanchanaratplace.component.BaseScaffold

@Composable
fun NotificationScaffold(navController: NavHostController){
    BaseScaffold(navController) {
        NotificationScreen(navController)
    }
}

@Composable
fun NotificationScreen(navController : NavHostController){

}