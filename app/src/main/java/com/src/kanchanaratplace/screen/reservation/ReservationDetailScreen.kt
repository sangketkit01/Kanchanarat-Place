package com.src.kanchanaratplace.screen.reservation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.src.kanchanaratplace.component.BaseScaffold
import com.src.kanchanaratplace.component.BlueWhiteButton
import com.src.kanchanaratplace.component.MytextField
import com.src.kanchanaratplace.component.WhiteBlueButton
import com.src.kanchanaratplace.navigation.Screen

@Composable
fun ReservationDetailScaffold(navController: NavHostController){
    BaseScaffold(navController) {
        ReservationDetailScreen(navController)
    }
}

@Composable
fun ReservationDetailScreen(navController : NavHostController){
    val scrollState = rememberScrollState()

    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        Card(
            modifier = Modifier.fillMaxWidth()
                .padding(20.dp),
            shape = RoundedCornerShape(20.dp),
            border = BorderStroke(
                width = 1.dp,
                color = Color.LightGray
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ){
            Column (
                modifier = Modifier.fillMaxWidth()
                    .padding(vertical = 20.dp, horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = "กรุณากรอกข้อมูลส่วนตัว",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.align(Alignment.Start)
                )

                MytextField(
                    value = name,
                    onValueChange = {name = it},
                    labelText = "ชื่อ"
                )

                MytextField(
                    value = surname,
                    onValueChange = {surname = it},
                    labelText = "นามสกุล"
                )

                MytextField(
                    value = phone,
                    onValueChange = {phone = it},
                    labelText = "เบอร์โทร"
                )

                MytextField(
                    value = email,
                    onValueChange = {email = it},
                    labelText = "อีเมลล์"
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Column (
            modifier = Modifier.fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            WhiteBlueButton(
                text = "กลับไปหน้าก่อนหน้านี้",
                onClick = {
                    navController.navigate(Screen.MakeReservation.route)
                }
            )


            Spacer(modifier = Modifier.height(10.dp))

            BlueWhiteButton(
                text = "ยืนยันการจองห้องพัก",
                onClick = {
                    navController.navigate(Screen.PayReservation.route)
                }
            )
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}