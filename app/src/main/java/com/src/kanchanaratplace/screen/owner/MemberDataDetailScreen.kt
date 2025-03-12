package com.src.kanchanaratplace.screen.owner

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.src.kanchanaratplace.R
import com.src.kanchanaratplace.component.SampleActionScaffold

@Composable
fun MemberDataDetailScaffold(navController: NavHostController){
    SampleActionScaffold(
        navController = navController,
        title = "ข้อมูลผู้เช่า",
        icon = Icons.Default.Edit,
        onClick = {

        }
    ) {
        MemberDataDetailScreen(navController)
    }
}

@Composable
fun MemberDataDetailScreen(navController : NavHostController){
    var name by remember { mutableStateOf("ใจดี") }
    var cardNumber by remember { mutableStateOf("1234567891234") }
    var phone by remember { mutableStateOf("081-2345678") }
    var email by remember { mutableStateOf("jaideejung@gmail.com") }

    var editPersonalAlert by remember { mutableStateOf(false) }
    var editRoomAlert by remember { mutableStateOf(false) }
    var editEmergencyAlert by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(
                color = Color.White
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        Spacer(modifier = Modifier.height(20.dp))

        Card (
            modifier = Modifier.fillMaxWidth()
                .padding(10.dp),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(
                width = 1.dp,
                color = Color(94, 144, 255, 255)
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ){
            Row (
                modifier = Modifier.fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(
                    painter = painterResource(R.drawable.member_example_profile),
                    contentScale = ContentScale.Fit,
                    contentDescription = null,
                    modifier = Modifier.size(45.dp)
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "ข้อมูลผู้เช่า: ใจดี",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(94, 144, 255, 255)
                )
            }
        }

        Card (
            modifier = Modifier.fillMaxWidth()
                .padding(10.dp),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            )
        ){
            Row (
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = 20.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Text(
                            text = "ชื่อ - นามสกุล:",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        Text(
                            text = "ใจดี ดีใจ",
                            fontSize = 16.sp
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Text(
                            text = "เลขบัตรประจำตัว:",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        Text(
                            text = "1234567891234",
                            fontSize = 16.sp
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Text(
                            text = "เบอร์โทรติดต่อ:",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        Text(
                            text = "081-2345678",
                            fontSize = 16.sp
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Text(
                            text = "อีเมลล์:",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        Text(
                            text = "jaidee@gmail.com",
                            fontSize = 16.sp
                        )
                    }
                }
                Button(
                    onClick = {
                        editPersonalAlert = true
                    },
                    shape = CircleShape,
                    border = BorderStroke(
                        width = 1.dp,
                        color = Color(94, 144, 255, 255)
                    ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color(94, 144, 255, 255)
                    )
                ) {
                    Text(
                        text = "แก้ไข",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    )
                }
            }
        }

        Card (
            modifier = Modifier.fillMaxWidth()
                .padding(10.dp),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            )
        ){
            Row (
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = 20.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Text(
                            text = "ข้อมูลการเช่า",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Text(
                            text = "ห้องที่เช่า:",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        Text(
                            text = "101",
                            fontSize = 16.sp
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Text(
                            text = "ระยะเวลาการเช่า:",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        Text(
                            text = "01/01/2567 - ปัจจุบัน",
                            fontSize = 16.sp
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Text(
                            text = "ค่ามัดจำ:",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        Text(
                            text = "4,000 บาท",
                            fontSize = 16.sp
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Text(
                            text = "ราคาค่าเช่ารายเดือน:",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        Text(
                            text = "4,000 บาท",
                            fontSize = 16.sp
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Text(
                            text = "สถานะการอาศัยอยู่:",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        Text(
                            text = "อยู่",
                            fontSize = 16.sp
                        )
                    }
                }
                Button(
                    onClick = {
                        editRoomAlert = true
                    },
                    shape = CircleShape,
                    border = BorderStroke(
                        width = 1.dp,
                        color = Color(94, 144, 255, 255)
                    ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color(94, 144, 255, 255)
                    )
                ) {
                    Text(
                        text = "แก้ไข",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    )
                }
            }
        }

        Card (
            modifier = Modifier.fillMaxWidth()
                .padding(10.dp),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            )
        ){
            Row (
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = 20.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Text(
                            text = "ข้อมูลฉุกเฉิน",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Text(
                            text = "ผู้ติดต่อฉุกเฉิน:",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        Text(
                            text = "ปรารถนา (แม่)",
                            fontSize = 16.sp
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Text(
                            text = "เบอร์ติดต่อ",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        Text(
                            text = "087-1234569",
                            fontSize = 16.sp
                        )
                    }
                }
                Button(
                    onClick = {
                        editEmergencyAlert = true
                    },
                    shape = CircleShape,
                    border = BorderStroke(
                        width = 1.dp,
                        color = Color(94, 144, 255, 255)
                    ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color(94, 144, 255, 255)
                    )
                ) {
                    Text(
                        text = "แก้ไข",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }

    if(editPersonalAlert){
        EditPersonalAlert(
            showAlert = editPersonalAlert,
            onShowAlert = {},
            name = name,
            onNameChange = {newValue->
                name = newValue
            },
            cardNumber = cardNumber,
            onCardNumberChange =  {newValue->
                cardNumber = newValue
            },
            phone = phone,
            onPhoneChange = {newValue->
                phone = newValue
            },
            email = email,
            onEmailChange = {newValue->
                email = newValue
            }
        )
    }

    if(editRoomAlert){

    }

    if(editEmergencyAlert){

    }
}

@Composable
fun EditPersonalAlert(
    showAlert : Boolean , onShowAlert : (Boolean) -> Unit,
    name : String, onNameChange : (String) -> Unit,
    cardNumber : String , onCardNumberChange : (String) -> Unit,
    phone : String, onPhoneChange : (String) -> Unit,
    email : String, onEmailChange : (String) -> Unit
){
    if(showAlert){
        AlertDialog(
            onDismissRequest = { onShowAlert(false) },
            text = {
                Column (
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 15.dp, vertical = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ){
                    Text(
                        text = "แก้ไขข้อมูล",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(94, 144, 255, 255)
                    )

                    OutlinedTextField(
                        value = name,
                        onValueChange = onNameChange,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White
                        ),
                        label = { Text("ชื่อ - นามสกุล") }
                    )

                    OutlinedTextField(
                        value = cardNumber,
                        onValueChange = onCardNumberChange,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White
                        ),
                        label = { Text("เลขประจำตัว") }
                    )

                    OutlinedTextField(
                        value = phone,
                        onValueChange = onPhoneChange,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White
                        ),
                        label = { Text("เบอร์โทรติดต่อ") }
                    )

                    OutlinedTextField(
                        value = email,
                        onValueChange = onEmailChange,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White
                        ),
                        label = { Text("อีเมลล์") }
                    )
                }
            },
            confirmButton = {

            },
        )
    }
}

