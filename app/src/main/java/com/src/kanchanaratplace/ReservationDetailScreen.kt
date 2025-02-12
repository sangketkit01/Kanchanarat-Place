package com.src.kanchanaratplace

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import com.src.kanchanaratplace.navigation.Screen

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
            .padding(vertical = 80.dp)
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

                OutlinedTextField(
                    value = name,
                    onValueChange = {newValue->
                        name = newValue
                    },
                    label = {
                        Text(
                            text = "ชื่อ",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(186, 184, 184, 255)
                        )
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedLabelColor = Color.LightGray,
                        focusedIndicatorColor = Color.LightGray,
                        unfocusedIndicatorColor = Color.LightGray
                    ),
                    modifier = Modifier.padding(vertical = 10.dp)
                        .fillMaxWidth()
                )

                OutlinedTextField(
                    value = surname,
                    onValueChange = {newValue->
                        surname = newValue
                    },
                    label = {
                        Text(
                            text = "นามสกุล",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(186, 184, 184, 255)
                        )
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedLabelColor = Color.LightGray,
                        focusedIndicatorColor = Color.LightGray,
                        unfocusedIndicatorColor = Color.LightGray
                    ),
                    modifier = Modifier.padding(vertical = 10.dp)
                        .fillMaxWidth()
                )

                OutlinedTextField(
                    value = phone,
                    onValueChange = {newValue->
                        phone = newValue
                    },
                    label = {
                        Text(
                            text = "เบอร์โทร",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(186, 184, 184, 255)
                        )
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedLabelColor = Color.LightGray,
                        focusedIndicatorColor = Color.LightGray,
                        unfocusedIndicatorColor = Color.LightGray
                    ),
                    modifier = Modifier.padding(vertical = 10.dp)
                        .fillMaxWidth()
                )

                OutlinedTextField(
                    value = email,
                    onValueChange = {newValue->
                        email = newValue
                    },
                    label = {
                        Text(
                            text = "อีเมลล์",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(186, 184, 184, 255)
                        )
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedLabelColor = Color.LightGray,
                        focusedIndicatorColor = Color.LightGray,
                        unfocusedIndicatorColor = Color.LightGray
                    ),
                    modifier = Modifier.padding(vertical = 10.dp)
                        .fillMaxWidth()
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Column (
            modifier = Modifier.fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            FilledTonalButton(
                onClick = {
                    navController.navigate(Screen.MakeReservation.route)
                },
                colors = ButtonDefaults.filledTonalButtonColors(
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .border(
                        width = 1.5.dp,
                        color = Color(94, 144, 255, 255),
                        shape = RoundedCornerShape(50.dp)
                    )
                    .width(347.dp).height(47.dp)
            ) {
                Text(
                    text = "กลับไปหน้าก่อนหน้านี้",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(94, 144, 255, 255)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            FilledTonalButton(
                onClick = {
                    navController.navigate(Screen.PayReservation.route)
                },
                colors = ButtonDefaults.filledTonalButtonColors(
                    containerColor = Color(94, 144, 255, 255)
                ),
                modifier = Modifier.width(347.dp).height(47.dp)
            ) {
                Text(
                    text = "ยินยันการจองห้องพัก",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(70.dp))
        }
    }
}