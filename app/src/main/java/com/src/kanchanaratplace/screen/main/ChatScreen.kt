package com.src.kanchanaratplace.screen.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.src.kanchanaratplace.component.BaseScaffold

@Composable
fun ChatScaffold(navController: NavHostController){
    BaseScaffold(navController) {
        ChatScreen(navController)
    }
}

@Composable
fun ChatScreen(navController : NavHostController){

}