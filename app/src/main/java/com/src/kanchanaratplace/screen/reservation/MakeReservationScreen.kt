package com.src.kanchanaratplace.screen.reservation

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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.src.kanchanaratplace.R
import com.src.kanchanaratplace.component.BaseScaffold
import com.src.kanchanaratplace.component.BlueWhiteButton
import com.src.kanchanaratplace.component.WhiteBlueButton
import com.src.kanchanaratplace.navigation.Screen

@Composable
fun MakeReservationScaffold(navController: NavHostController){
    BaseScaffold(navController) {
        MakeReservationScreen(navController)
    }
}

@Composable
fun MakeReservationScreen(navController : NavHostController){
    val room = navController.previousBackStackEntry?.savedStateHandle?.get<String>("room")
    val roomId = navController.previousBackStackEntry?.savedStateHandle?.get<Int>("room_id")
    val scrollState = rememberScrollState()

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
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

        WhiteBlueButton(
            text = "กลับไปหน้าก่อนหน้านี้",
            onClick = {
                navController.navigate(Screen.Reservation.route)
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

        BlueWhiteButton(
            text = "จองห้องพัก",
            onClick = {
                navController.currentBackStackEntry?.savedStateHandle?.set(
                    "room" , room
                )
                navController.currentBackStackEntry?.savedStateHandle?.set(
                    "room_id",roomId
                )
                navController.navigate(Screen.ReservationDetail.route)
            }
        )

        Spacer(modifier = Modifier.height(40.dp))
    }

}