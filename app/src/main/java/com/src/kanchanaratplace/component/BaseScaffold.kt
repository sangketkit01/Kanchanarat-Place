package com.src.kanchanaratplace.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.src.kanchanaratplace.navigation.AdminBottomBar
import com.src.kanchanaratplace.navigation.AuthenticatedTopBar
import com.src.kanchanaratplace.navigation.BottomBar
import com.src.kanchanaratplace.navigation.UnAuthenticationTopBar
import com.src.kanchanaratplace.screen.main.FirstScreen
import com.src.kanchanaratplace.session.MemberSharePreferencesManager
import com.src.kanchanaratplace.status.Role

@Composable
fun BaseScaffold(navController : NavHostController , content : @Composable () -> Unit){
    val context = LocalContext.current
    val sharePreferences = remember { MemberSharePreferencesManager(context) }

    Scaffold(
        topBar = {
            if(sharePreferences.loggedIn){
                AuthenticatedTopBar(navController)
            }else{
                UnAuthenticationTopBar(navController)
            }
        },
        bottomBar = {
            if(sharePreferences.member?.roleId == Role.OWNER.code){
                AdminBottomBar(navController)
            }else{
                BottomBar(navController)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            content()
        }

    }
}