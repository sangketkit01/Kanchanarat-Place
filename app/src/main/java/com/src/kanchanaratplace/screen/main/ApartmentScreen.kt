package com.src.kanchanaratplace.screen.main

import androidx.compose.foundation.background
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.src.kanchanaratplace.component.BaseScaffold
import com.src.kanchanaratplace.navigation.Screen

@Composable
fun ApartmentScaffold(navController: NavHostController){
    BaseScaffold(navController) {
        ApartmentScreen(navController)
    }
}

@Composable
fun ApartmentScreen(navController : NavHostController){
    val scrollState = rememberScrollState()

    Column (
        modifier = Modifier.fillMaxSize()
            .verticalScroll(scrollState)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        Spacer(Modifier.height(40.dp))
        Text(
            text = "ห้องพัก",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.align(Alignment.Start)
                .padding(horizontal = 10.dp, vertical = 5.dp)
        )

        Card (
            modifier = Modifier.fillMaxWidth()
                .padding(10.dp),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(172, 198, 255, 255)
            )
        ) {
            Row (
                modifier = Modifier.padding(15.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ){
                Column (
                    modifier = Modifier.width(158.dp).height(120.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.White)
                        .clickable {

                        },
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(
                        text = "จัดการห้องพัก",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Column (
                    modifier = Modifier.width(158.dp).height(120.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.White)
                        .clickable {
                            navController.navigate(Screen.Bills.route)
                        },
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(
                        text = "แจ้งชำระค่าบริการ",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }

        Text(
            text = "รับเรื่องแจ้งซ่อม",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.align(Alignment.Start)
                .padding(horizontal = 10.dp, vertical = 5.dp)
        )

        Card (
            modifier = Modifier.fillMaxWidth()
                .padding(10.dp),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(172, 198, 255, 255)
            )
        ) {
            Row (
                modifier = Modifier.padding(15.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ){
                Column (
                    modifier = Modifier.width(158.dp).height(120.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.White)
                        .clickable {  },
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(
                        text = "จัดการห้องพัก",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Column (
                    modifier = Modifier.width(158.dp).height(120.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.White)
                        .clickable {  },
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(
                        text = "แจ้งชำระค่าบริการ",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }

        Text(
            text = "อนุมัติการจอง",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.align(Alignment.Start)
                .padding(horizontal = 10.dp, vertical = 5.dp)
        )

        Card (
            modifier = Modifier.fillMaxWidth()
                .padding(10.dp),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(172, 198, 255, 255)
            )
        ) {
            Row (
                modifier = Modifier.padding(15.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ){
                Column (
                    modifier = Modifier.width(158.dp).height(120.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.White)
                        .clickable {
                            navController.navigate(Screen.ReservedList.route)
                        },
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(
                        text = "ดูการจอง",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Column (
                    modifier = Modifier.width(158.dp).height(120.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.White)
                        .clickable {  },
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(
                        text = "แจ้งชำระค่าบริการ",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}