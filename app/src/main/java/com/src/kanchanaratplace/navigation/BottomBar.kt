package com.src.kanchanaratplace.navigation

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.src.kanchanaratplace.session.MemberSharePreferencesManager
import com.src.kanchanaratplace.component.LoginRequiredDialog
import com.src.kanchanaratplace.component.UnAuthorizedAlert
import com.src.kanchanaratplace.status.Role

@Composable
fun BottomBar(navController : NavHostController){
    val navigationItems = listOf(Screen.First,Screen.MemberApartment,Screen.Chat,Screen.Profile,Screen.Notification)
    var selectedScreen by remember { mutableIntStateOf(0) }

    val context = LocalContext.current.applicationContext

    lateinit var sharePreferences : MemberSharePreferencesManager
    sharePreferences = MemberSharePreferencesManager(context)

    var alertDialog by remember { mutableStateOf(false) }
    var notAuthorizedAlert by remember { mutableStateOf(false) }

    NavigationBar(
        containerColor = Color(94, 144, 255, 255),
        modifier = Modifier.height(125.dp)
    ) {
        navigationItems.forEachIndexed { index, screen ->
            NavigationBarItem(
                icon = {
                    when (screen.icon) {
                        is Int -> Icon(
                            painter = painterResource(id = screen.icon),
                            contentDescription = null,
                            modifier = Modifier.size(33.dp),
                            tint = Color.White
                        )
                        is ImageVector -> Icon(
                            imageVector = screen.icon,
                            contentDescription = null,
                            modifier = Modifier.size(33.dp)
                        )
                    }
                },
                label = {
                    Text(
                        text = screen.name,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                selected = false,
                onClick = {
                    if(screen.route != Screen.First.route && !sharePreferences.loggedIn){
                        alertDialog = true
                    }
                    else{
                        selectedScreen = index
                        navController.navigate(screen.route)
                    }

                },
            )
        }
    }

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

    if(notAuthorizedAlert){
        UnAuthorizedAlert(
            notAuthorizedAlert,
            onDismiss = {notAuthorizedAlert = false}
        )
    }

}

@Composable
fun AdminBottomBar(navController: NavHostController){
    val navigationItems = listOf(Screen.HomeAdmin,Screen.Meter,Screen.Bills,
        Screen.Profile,Screen.ProfileAdmin)
    var selectedScreen by remember { mutableIntStateOf(0) }

    val context = LocalContext.current.applicationContext

    lateinit var sharePreferences : MemberSharePreferencesManager
    sharePreferences = MemberSharePreferencesManager(context)

    var alertDialog by remember { mutableStateOf(false) }
    var notAuthorizedAlert by remember { mutableStateOf(false) }

    NavigationBar(
        containerColor = Color(94, 144, 255, 255),
        modifier = Modifier.height(125.dp)
    ) {
        navigationItems.forEachIndexed { index, screen ->
            NavigationBarItem(
                icon = {
                    when (screen.icon) {
                        is Int -> Icon(
                            painter = painterResource(id = screen.icon),
                            contentDescription = null,
                            modifier = Modifier.size(33.dp),
                            tint = Color.White
                        )
                        is ImageVector -> Icon(
                            imageVector = screen.icon,
                            contentDescription = null,
                            modifier = Modifier.size(33.dp),
                            tint = Color.White
                        )
                    }
                },
                label = {
                    Text(
                        text = screen.name,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                },
                selected = false,
                onClick = {
                    if(screen.route != Screen.First.route && !sharePreferences.loggedIn){
                        alertDialog = true
                    }else if (screen.route == Screen.Apartment.route && sharePreferences.member?.roleId != Role.OWNER.id){
                        notAuthorizedAlert = true
                    }
                    else{
                        selectedScreen = index
                        navController.navigate(screen.route)
                    }

                },
            )
        }
    }

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

    if(notAuthorizedAlert){
        UnAuthorizedAlert(
            notAuthorizedAlert,
            onDismiss = {notAuthorizedAlert = false}
        )
    }
}

