package com.src.kanchanaratplace

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun LoginScreen(navController : NavHostController){
    val scrollState = rememberScrollState()

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(vertical = 80.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        Spacer(modifier = Modifier.height(20.dp))

        Card (
            modifier = Modifier.padding(10.dp).clip(RoundedCornerShape(10.dp))
                .border(
                    width = 1.dp,
                    color = Color.LightGray,
                    shape = RoundedCornerShape(10.dp)
                ),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ){
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 15.dp,
                        end = 15.dp,
                        top = 15.dp,
                        bottom = 50.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = "เข้าสู่ระบบ",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(10.dp)
                )

                OutlinedTextField(
                    modifier = Modifier.padding(10.dp)
                        .fillMaxWidth().clip(RoundedCornerShape(10.dp)),
                    value = username,
                    onValueChange = {newUsername->
                        username = newUsername
                    },
                    label = { Text(
                        text="ชื่อผู้ใช้",
                        fontWeight = FontWeight.SemiBold
                    ) },
                    leadingIcon = { Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                    ) },
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        unfocusedLabelColor = Color.LightGray,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = Color.LightGray,
                        unfocusedIndicatorColor = Color.LightGray
                    ),
                    textStyle = TextStyle(fontWeight = FontWeight.SemiBold)
                )

                OutlinedTextField(
                    modifier = Modifier.padding(10.dp)
                        .fillMaxWidth().clip(RoundedCornerShape(10.dp)),
                    value = password,
                    onValueChange = {newPassword->
                        password = newPassword
                    },
                    label = { Text(
                        text="รหัสผ่าน",
                        fontWeight = FontWeight.SemiBold
                    ) },
                    leadingIcon = { Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                    ) },
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        unfocusedLabelColor = Color.LightGray,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = Color.LightGray,
                        unfocusedIndicatorColor = Color.LightGray
                    ),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    textStyle = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                )

                Row (
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "ลืมรหัสผ่าน?",
                        fontSize = 16.sp,
                        color = Color(186, 184, 184, 255),
                        modifier = Modifier.clickable {

                        }.clip(RoundedCornerShape(10.dp)),
                        textDecoration = TextDecoration.Underline
                    )
                }

                Spacer(modifier = Modifier.height(60.dp))

                FilledTonalButton(
                    onClick = {

                    },
                    colors = ButtonDefaults.filledTonalButtonColors(
                        containerColor = Color(94, 144, 255, 255)
                    ),
                    modifier = Modifier
                        .padding(horizontal = 10.dp).width(171.dp)
                ) {
                    Text(
                        text = "เข้าสู่ระบบ",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
            }
        }
    }
}