package com.src.kanchanaratplace.screen.main

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.src.kanchanaratplace.R
import com.src.kanchanaratplace.component.BaseScaffold
import com.src.kanchanaratplace.component.BlueWhiteButton
import com.src.kanchanaratplace.component.SampleScaffold
import com.src.kanchanaratplace.component.WhiteBlueButton
import com.src.kanchanaratplace.navigation.AuthenticatedTopBar
import com.src.kanchanaratplace.navigation.BottomBar
import com.src.kanchanaratplace.navigation.Screen
import com.src.kanchanaratplace.navigation.UnAuthenticationTopBar
import com.src.kanchanaratplace.screen.reservation.ReservationStatusScreen
import com.src.kanchanaratplace.session.MemberSharePreferencesManager

@Composable
fun ApartmentContractScaffold(navController : NavHostController){
    SampleScaffold(navController,"ช่องทางการติดต่อ") {
        ApartmentContractScreen(navController)
    }
}

@Composable
fun ApartmentContractScreen(navController : NavHostController){
    val scrollState = rememberScrollState()

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        Spacer(modifier = Modifier.height(20.dp))

        Card (
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
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ){
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ){
                    Text(
                        text = "ช่องทางการติดต่อหอพัก",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Row (
                    modifier = Modifier.fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .border(
                            width = 1.dp,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(10.dp)
                        ).height(55.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ){
                    Spacer(modifier = Modifier.width(10.dp))

                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = "081-234-5678",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Row (
                    modifier = Modifier.fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .border(
                            width = 1.dp,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(10.dp)
                        ).height(55.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ){

                    Spacer(modifier = Modifier.width(10.dp))

                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = "Kanchanarat@gmail.com",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Row (
                    modifier = Modifier.fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .border(
                            width = 1.dp,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(10.dp)
                        ).height(55.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ){
                    Spacer(modifier = Modifier.width(10.dp))

                    Icon(
                        painter = painterResource(R.drawable.line),
                        contentDescription = null,
                        modifier = Modifier.size(27.dp)
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = "Kanchanarat",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Image(
                    painter = painterResource(R.drawable.line_qr),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(151.dp)
                )
            }
        }

        Column (
            modifier = Modifier.fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            WhiteBlueButton(
                text = "กลับไปที่หน้าหลัก",
                onClick = {
                    navController.navigate(Screen.First.route)
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            BlueWhiteButton(
                text = "เข้าสู่ระบบ",
                onClick = {
                    navController.navigate(Screen.Login.route)
                }
            )

        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}