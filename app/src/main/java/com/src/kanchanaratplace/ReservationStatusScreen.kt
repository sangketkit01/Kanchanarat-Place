package com.src.kanchanaratplace

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.src.kanchanaratplace.navigation.Screen

@Composable
fun ReservationStatusScreen(navController : NavHostController){
    val scrollState = rememberScrollState()

    var currentStep by remember { mutableIntStateOf(3) }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 200.dp,bottom = 80.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        StepProgressIndicator(currentStep = currentStep)

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "ระบบดำเนินการแจ้งหอพักเรียบร้อย และรอดำเนินการในขั้นต่อไป",
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            color = Color(128, 128, 128, 255),
            modifier = Modifier.width(305.dp),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(80.dp))

        Column (
            modifier = Modifier.fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            if(currentStep == 3){
                FilledTonalButton(
                    onClick = {
                        navController.navigate(Screen.ContractFee.route)
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
                        text = "ดำเนินการทำสัญญา",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(94, 144, 255, 255)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            FilledTonalButton(
                onClick = {
                    navController.navigate(Screen.First.route)
                },
                colors = ButtonDefaults.filledTonalButtonColors(
                    containerColor = Color(94, 144, 255, 255)
                ),
                modifier = Modifier.width(347.dp).height(47.dp)
            ) {
                Text(
                    text = "กลับไปที่หน้าหลัก",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(70.dp))
        }
    }
}

@Composable
fun StepProgressIndicator(currentStep: Int, totalSteps: Int = 3) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        for (i in 1..totalSteps) {
            if (i > 1) {
                Box(
                    modifier = Modifier
                        .width(62.dp)
                        .height(2.dp)
                        .background(if (i <= currentStep) Color(94, 144, 255, 255) else Color.LightGray)
                )
            }
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(
                        color = if (i <= currentStep) Color(94, 144, 255, 255) else Color.White,
                        shape = CircleShape
                    )
                    .border(1.dp, if (i <= currentStep) Color(94, 144, 255, 255) else Color.LightGray, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = i.toString(),
                    color = if (i <= currentStep) Color.White else Color(94, 144, 255, 255),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

