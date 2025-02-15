package com.src.kanchanaratplace.screen.main

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.src.kanchanaratplace.component.BaseScaffold
import com.src.kanchanaratplace.navigation.Screen
import com.src.kanchanaratplace.session.MemberSharePreferencesManager

@Composable
fun SettingScaffold(navController: NavHostController){
    BaseScaffold(navController) {
        SettingScreen(navController)
    }
}


@Composable
fun SettingScreen(navController : NavHostController){
    val context = LocalContext.current.applicationContext
    val scrollState = rememberScrollState()

    lateinit var sharePreferences : MemberSharePreferencesManager
    sharePreferences = MemberSharePreferencesManager(context)

    var alertDialog by remember { mutableStateOf(false) }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(Color.Transparent),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ){

        Spacer(Modifier.height(40.dp))

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp, horizontal = 10.dp)
        ){
            Text("บัญชีของฉัน", fontWeight = FontWeight.SemiBold)
        }

        Card (
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(1.dp, Color.LightGray),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ){

            Row (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
                    .clickable {
                        Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show()
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {

                    Text(
                        text = "บัญชีแล้วความปลอดภัย",
                        fontSize = 15.sp,
                    )
                }

                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                )
            }

            Divider(modifier = Modifier.padding(vertical = 8.dp))

            Row (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
                    .clickable {
                        Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show()
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {

                    Text(
                        text = "ที่พักของฉัน",
                        fontSize = 15.sp,
                    )
                }

                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                )
            }
        }

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp, horizontal = 10.dp)
        ){
            Text("ช่วยเหลือ", fontWeight = FontWeight.SemiBold)
        }

        Card (
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(1.dp, Color.LightGray),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ){

            Row (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
                    .clickable {
                        Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show()
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {

                    Text(
                        text = "ศูนย์ช่วยเหลือ",
                        fontSize = 15.sp,
                    )
                }

                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                )
            }

        }

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp, horizontal = 10.dp)
        ){
            Text("ตั้งค่า", fontWeight = FontWeight.SemiBold)
        }

        Card (
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(1.dp, Color.LightGray),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ){

            Row (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
                    .clickable {
                        Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show()
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {

                    Text(
                        text = "ตั้งค่าการแจ้งเตือน",
                        fontSize = 15.sp,
                    )
                }

                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                )
            }

            Divider(modifier = Modifier.padding(vertical = 8.dp))

            Row (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
                    .clickable {
                        Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show()
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {

                    Text(
                        text = "ภาษา/Language",
                        fontSize = 15.sp,
                    )
                }

                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                )
            }

        }
        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                alertDialog = true
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .height(50.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ExitToApp,
                contentDescription = "Logout",
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Logout", color = Color.White, fontWeight = FontWeight.Bold)
        }

    }

    if (alertDialog){
        AlertDialog(
            onDismissRequest = {alertDialog = false},
            title = {
                Text("คำเตือน")
            },
            text = {
                Text("คุณต้องการออกจากระบบใช่หรือไม่")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        sharePreferences.clear()
                        navController.navigate(Screen.Login.route)
                    }
                ) {
                    Text("ตกลง")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        alertDialog = false
                    }
                ) {
                    Text("ยกเลิก")
                }
            },
            containerColor = Color.White,
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = Color.LightGray,
                    shape = RoundedCornerShape(16.dp)
                ),
            shape = RoundedCornerShape(16.dp)
        )
    }
}