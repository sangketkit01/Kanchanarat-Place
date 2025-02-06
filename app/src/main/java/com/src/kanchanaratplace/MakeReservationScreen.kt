package com.src.kanchanaratplace

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.src.kanchanaratplace.data.MakeReservation
import com.src.kanchanaratplace.navigation.Screen

@Composable
fun MakeReservationScreen(navController : NavHostController){
    val room = navController.previousBackStackEntry?.savedStateHandle?.get<String>("room")
    val scrollState = rememberScrollState()
    var reservationDialog by remember { mutableStateOf(false) }

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

        Spacer(Modifier.height(20.dp))

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .border(
                    width = 1.dp,
                    color = Color.LightGray,
                    shape = RoundedCornerShape(10.dp)
                ),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Image(
                painter = painterResource(id = R.drawable.reservation_1),
                contentDescription = null,
                modifier = Modifier
                    .width(135.dp)
                    .height(182.dp)
                    .padding(5.dp)
            )

            Column(
                modifier = Modifier.padding(5.dp)
            ) {
                Text(
                    text = "หอกาญจนารัตน์ เพลส",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Text("ห้อง $room")
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(1.dp, Color.LightGray),
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "สรุปการจอง",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "ราคาห้อง", fontSize = 16.sp)
                    Text(text = "4,000 บาท", fontSize = 16.sp)
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "เงินมัดจำ/ประกัน", fontSize = 16.sp)
                    Text(text = "4,000 บาท", fontSize = 16.sp)
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "จ่ายล่วงหน้า", fontSize = 16.sp)
                    Text(text = "4,000 บาท", fontSize = 16.sp)
                }

                Divider(modifier = Modifier.padding(vertical = 8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "รวมทั้งหมด",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Text(
                        text = "12,000 บาท",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        FilledTonalButton(
            onClick = {
                navController.navigate(Screen.Reservation.route)
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
                reservationDialog = true
            },
            colors = ButtonDefaults.filledTonalButtonColors(
                containerColor = Color(94, 144, 255, 255)
            ),
            modifier = Modifier.width(347.dp).height(47.dp)
        ) {
            Text(
                text = "จองห้องพัก",
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(70.dp))

    }

    if(reservationDialog){
        AlertDialog(
            containerColor = Color.White,
            modifier = Modifier.clip(RoundedCornerShape(10.dp))
                .border(
                    width = 1.dp,
                    color = Color.LightGray
                ),
            shape = RoundedCornerShape(10.dp),
            onDismissRequest = {reservationDialog = false},
            title = { Text("กรอกข้อมูลส่วนตัว") },
            text = {
                Column {
                    OutlinedTextField(
                        value = name,
                        onValueChange = {newName->
                            name = newName
                        },
                        label = { Text("กรุณากรอกชื่อ") },
                        modifier = Modifier.padding(10.dp).clip(RoundedCornerShape(10.dp)),
                        shape = RoundedCornerShape(10.dp)
                    )

                    OutlinedTextField(
                        value = surname,
                        onValueChange = {newSurname->
                            surname = newSurname
                        },
                        label = { Text("นามสกุล") },
                        modifier = Modifier.padding(10.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        shape = RoundedCornerShape(10.dp)
                    )

                    OutlinedTextField(
                        value = phone,
                        onValueChange = {newPhone->
                            phone = newPhone
                        },
                        label = { Text("เบอร์โทร") },
                        modifier = Modifier.padding(10.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        shape = RoundedCornerShape(10.dp),
                        enabled = phone.length < 10,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )

                    OutlinedTextField(
                        value = email,
                        onValueChange = {newEmail->
                            email = newEmail
                        },
                        label = { Text("อีเมลล์") },
                        modifier = Modifier.padding(10.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        shape = RoundedCornerShape(10.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                    )
                }

            },
            confirmButton = {
                FilledTonalButton(
                    onClick = {
                        navController.currentBackStackEntry?.savedStateHandle?.set(
                            "data", MakeReservation(name,surname,phone,email)
                        )

                        navController.navigate(Screen.PayReservation.route)
                    },
                    colors = ButtonDefaults.filledTonalButtonColors(
                        containerColor = Color(94, 144, 255, 255)
                    ),
                    modifier = Modifier
                            .border(
                            width = 1.dp,
                    color = Color.Transparent,
                    shape = RoundedCornerShape(50.dp)
                )
                ) {
                    Text(
                        text = "ยืนยันการจอง",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
            },
            dismissButton = {
                FilledTonalButton(
                    onClick = {
                        reservationDialog = false
                    },
                    colors = ButtonDefaults.filledTonalButtonColors(
                        containerColor = Color.White
                    ),
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color(94, 144, 255, 255),
                            shape = RoundedCornerShape(50.dp)
                        )
                ) {
                    Text(
                        text = "ยกเลิก",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(94, 144, 255, 255)
                    )
                }
            }
        )
    }
}