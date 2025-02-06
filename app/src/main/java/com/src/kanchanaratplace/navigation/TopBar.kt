package com.src.kanchanaratplace.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

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
                    navController.navigate(setting.route)
                }
            ) {
                when (setting.icon) {
                    is Int -> Icon(painter = painterResource(id = setting.icon), contentDescription = null)
                    is ImageVector -> Icon(imageVector = setting.icon, contentDescription = null)
                }
            }
        }
    )
}