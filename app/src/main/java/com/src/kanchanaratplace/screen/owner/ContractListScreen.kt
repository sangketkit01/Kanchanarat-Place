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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.src.kanchanaratplace.R
import com.src.kanchanaratplace.component.ButtonWithBadge
import com.src.kanchanaratplace.component.SampleScaffold
import com.src.kanchanaratplace.navigation.Screen

@Composable
fun ContractListScaffold(navController: NavHostController){
    SampleScaffold(navController,"สัญญา") {
        ContractListScreen(navController)
    }
}

@Composable
fun ContractListScreen(navController : NavHostController){
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier.fillMaxSize()
            .verticalScroll(scrollState)
            .background(
                color = Color.White
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(240, 240, 240))
        ) {
            ButtonWithBadge(
                text = "อนุมัติการจอง",
                badgeCount = 1,
                modifier = Modifier.weight(1f),
                background = Color(240, 240, 240, 255),
                onClick = {
                    navController.navigate(Screen.ReservedList.route)
                }
            )
            ButtonWithBadge(
                text = "ทำสัญญา",
                badgeCount = 1,
                modifier = Modifier.weight(1f),
                background = Color.White,
                onClick = {
                    navController.navigate(Screen.ContractList.route)
                }
            )
        }

        Card (
            modifier = Modifier.fillMaxWidth()
                .padding(10.dp),
            border = BorderStroke(
                width = 2.dp,
                color = Color.LightGray,
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(10.dp)
        ){
            Row (
                modifier = Modifier.fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Image(
                    painter = painterResource(R.drawable.purple_house),
                    contentDescription = null,
                    modifier = Modifier.size(60.dp)
                )

                Spacer(modifier = Modifier.width(10.dp))

                Column {
                    Text(
                        text = "ชั้น 1 ห้อง 109",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = "คุณใจดี ดีใจจัง",
                        fontSize = 16.sp
                    )

                    Text(
                        text = "15 กุมภาพันธ์ 2568 16:55",
                        fontSize = 12.sp,
                        color = Color(165, 165, 165, 255)
                    )
                }

                Spacer(modifier = Modifier.width(30.dp))

                Button(
                    onClick = {
                        navController.navigate(Screen.ContractDetail.route)
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(94, 144, 255, 255),
                        contentColor = Color.White
                    ),
                ) {
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ){
                        Icon(
                            painter = painterResource(R.drawable.note),
                            contentDescription = null,
                            modifier = Modifier.size(35.dp)
                        )

                        Text(
                            text = "รายละเอียด",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}