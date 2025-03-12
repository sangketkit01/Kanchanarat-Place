package com.src.kanchanaratplace.screen.owner

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import com.src.kanchanaratplace.component.SampleScaffold
import com.src.kanchanaratplace.component.SmallWhiteBlueButton
import com.src.kanchanaratplace.navigation.Screen

@Composable
fun MemberDataListScaffold(navController: NavHostController){
    SampleScaffold(navController,"ข้อมูลผู้เช่า") {
        MemberDataListScreen(navController)
    }
}

@Composable
fun MemberDataListScreen(navController : NavHostController){
    var selectedFloor by remember { mutableIntStateOf(1) }

    val scrollState = rememberScrollState()

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        Row(
            modifier = Modifier.fillMaxWidth()
                .background(
                    color = Color(240, 240, 240, 255)
                ),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("ชั้น: ", fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
            for (floor in 1..5) {
                Button(
                    onClick = { selectedFloor = floor },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedFloor == floor) Color(94, 144, 255, 255) else Color.Transparent
                    ),
                    modifier = Modifier.padding(horizontal = 4.dp)
                ) {
                    Text(
                        text = "$floor",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = if (selectedFloor == floor) Color.White else Color.Black
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        Button(
            onClick = {

            },
            shape = CircleShape,
            border = BorderStroke(
                width = 1.dp,
                color = Color(94, 144, 255, 255)
            ),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color(94, 144, 255, 255)
            ),
            modifier = Modifier.align(Alignment.End).padding(end = 10.dp)
        ) {
            Text(
                text = "เพิ่มผู้เช่า",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        for (i in 0 .. 5){
            Card (
                modifier = Modifier.fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 15.dp),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(172, 198, 255, 255)
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                )
            ){
                Row (
                    modifier = Modifier.fillMaxWidth()
                        .padding(15.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Image(
                        painter = painterResource(R.drawable.member_example_profile),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.size(60.dp)
                    )

                     Column (
                         horizontalAlignment = Alignment.CenterHorizontally
                     ){
                         Text(
                             text = "ชื่อผู้เช่า",
                             fontSize = 14.sp,
                             fontWeight = FontWeight.SemiBold,
                             color = Color(112, 110, 110, 255)
                         )

                         Text(
                             text = "ใจดี",
                             fontSize = 16.sp,
                             fontWeight = FontWeight.SemiBold,
                             color = Color(112, 110, 110, 255)
                         )
                     }

                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Text(
                            text = "ห้อง",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(112, 110, 110, 255)
                        )

                        Text(
                            text = "101",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(112, 110, 110, 255)
                        )
                    }

                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Text(
                            text = "วันที่เข้าพัก",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(112, 110, 110, 255)
                        )

                        Text(
                            text = "1 ม.ค. 67",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(112, 110, 110, 255)
                        )
                    }

                    Button(
                        onClick = {
                            navController.navigate(Screen.MemberDataDetail.route)
                        },
                        shape = CircleShape,
                        border = BorderStroke(
                            width = 1.dp,
                            color = Color(94, 144, 255, 255)
                        ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color(94, 144, 255, 255)
                        ),
                        contentPadding = PaddingValues(8.dp)
                    ) {
                        Text(
                            text = "ดูรายละเอียด",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}