package com.src.kanchanaratplace.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.src.kanchanaratplace.navigation.AdminBottomBar
import com.src.kanchanaratplace.navigation.AuthenticatedTopBar
import com.src.kanchanaratplace.navigation.BottomBar
import com.src.kanchanaratplace.navigation.SampleActionTopAppBar
import com.src.kanchanaratplace.navigation.SampleTopAppBar
import com.src.kanchanaratplace.navigation.UnAuthenticationTopBar
import com.src.kanchanaratplace.session.MemberSharePreferencesManager
import com.src.kanchanaratplace.status.Role

@Composable
fun SampleScaffold(navController : NavHostController,title : String ,content : @Composable () -> Unit){
    val context = LocalContext.current
    val sharePreferences = remember { MemberSharePreferencesManager(context) }

    Scaffold(
        topBar = {
            SampleTopAppBar(navController,title)
        },
        bottomBar = {
            if(sharePreferences.member?.roleId == Role.OWNER.id){
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

@Composable
fun SampleActionScaffold(navController: NavHostController , title: String,
                         icon : ImageVector,  onClick : ()-> Unit,
                         content:@Composable () -> Unit){

    val context = LocalContext.current
    val sharePreferences = remember { MemberSharePreferencesManager(context) }

    Scaffold(
        topBar = {
            SampleActionTopAppBar(
                navController = navController,
                title = title,
                onClick = onClick,
                icon = icon
            )
        },
        bottomBar = {
            if(sharePreferences.member?.roleId == Role.OWNER.id){
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