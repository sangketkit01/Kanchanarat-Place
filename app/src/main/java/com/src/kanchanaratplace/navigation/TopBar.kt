package com.src.kanchanaratplace.navigation

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.src.kanchanaratplace.session.MemberSharePreferencesManager
import com.src.kanchanaratplace.Component.LoginRequiredDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnAuthenticationTopBar(navController : NavHostController){
    TopAppBar(
        title = {
            Text(
                text = "Kanchanarat Place",
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color(94, 144, 255, 255)
        ),
        actions = {
            FilledTonalButton(
                onClick = {
                    navController.navigate(Screen.Login.route)
                },
                colors = ButtonDefaults.filledTonalButtonColors(
                    containerColor = Color.White
                )
            ) {
                Text(
                    text = "เข้าสู่ระบบ",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(94, 144, 255, 255)
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthenticatedTopBar(navController: NavHostController){
    var alertDialog by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val sharePreferences = remember { MemberSharePreferencesManager(context) }

    val setting = Screen.Setting
    TopAppBar(
        title = {
            Text(
                text = "Kanchanarat Place",
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color(94, 144, 255, 255)
        ),
        actions = {
            IconButton(
                onClick = {
                    if(sharePreferences.loggedIn){
                        navController.navigate(setting.route)
                    }else{
                        alertDialog = true
                    }

                }
            ) {
                when (setting.icon) {
                    is Int -> Icon(painter = painterResource(id = setting.icon), contentDescription = null)
                    is ImageVector -> Icon(imageVector = setting.icon, contentDescription = null)
                }
            }
        }
    )

    if(alertDialog){
        LoginRequiredDialog(
            showDialog = alertDialog,
            onDismiss = {alertDialog = false},
            onNavigateToHome = {
                alertDialog = false
                navController.navigate(Screen.First.route)
            },
            onNavigateToLogin = {
                alertDialog = false
                navController.navigate(Screen.Login.route)
            }
        )
    }
}