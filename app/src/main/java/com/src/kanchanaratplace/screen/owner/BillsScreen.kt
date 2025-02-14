package com.src.kanchanaratplace.screen.owner

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.src.kanchanaratplace.component.BaseScaffold

@Composable
fun BillsScaffold(navController: NavHostController){
    BaseScaffold(navController) {
        BillsScreen(navController)
    }
}

@Composable
fun BillsScreen(navController : NavHostController){

}