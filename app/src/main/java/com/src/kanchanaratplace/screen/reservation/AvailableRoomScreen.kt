package com.src.kanchanaratplace.screen.reservation

import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.src.kanchanaratplace.R
import com.src.kanchanaratplace.component.BaseScaffold
import com.src.kanchanaratplace.navigation.Screen

@Composable
fun AvailableRoomScaffold(navController: NavHostController){
    BaseScaffold(navController) {
        AvailableRoomScreen(navController)
    }
}

@Composable
fun AvailableRoomScreen(navController : NavHostController){
    val scrollState = rememberScrollState()
    var selectedFloor by  remember { mutableIntStateOf(1) }
    val examplePic = listOf(
        R.drawable.example_pic6, R.drawable.example_pic7, R.drawable.example_pic8
        , R.drawable.example_pic9
    )
    Column (
        modifier = Modifier.fillMaxSize()
            .verticalScroll(scrollState).padding(vertical = 80.dp)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            IconButton(
                onClick = {
                    navController.navigate(Screen.ApartmentDetail.route)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null
                )
            }


            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = "แผนผังหอพัก",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Row (
            modifier = Modifier.fillMaxWidth()
                .background(
                    color = Color(240, 240, 240, 255)
                ),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text("ชั้น: ", fontSize = 15.sp, fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 10.dp))

            for (floor in 1..5) {
                Button(
                    onClick = {
                        selectedFloor = floor
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedFloor == floor) {
                            Color(94, 144, 255, 255)
                        } else {
                            Color.Transparent
                        }
                    ),
                    modifier = Modifier.padding(horizontal = 4.dp)
                ) {
                    Text(
                        text = "$floor",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = if (selectedFloor == floor) {
                            Color.White
                        } else {
                            Color.Black
                        }
                    )
                }
            }

        }

        Spacer(modifier = Modifier.height(20.dp))


        Image(
            painter = when{
                selectedFloor == 1 -> {painterResource(id = R.drawable.available_1_final)}
                selectedFloor == 2 -> {painterResource(id = R.drawable.available_2)}
                selectedFloor == 3 -> {painterResource(id = R.drawable.available_3)}
                selectedFloor == 4 -> {painterResource(id = R.drawable.available_4)}
                else -> {painterResource(id = R.drawable.available_5)}
            },
            contentDescription = null,
            modifier = Modifier.width(342.dp).height(160.dp)
        )

        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.width(342.dp)
        ){
            Column {
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .background(Color.Red, CircleShape)
                    )

                    Spacer(modifier = Modifier.width(5.dp))

                    Text("ห้องไม่ว่าง", fontSize = 16.sp)
                }

                Row (
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .background(Color.Green, CircleShape)
                    )
                    Spacer(modifier = Modifier.width(5.dp))

                    Text("ห้องว่าง", fontSize = 16.sp)
                }

            }

            FilledTonalButton(
                onClick = {
                    navController.navigate(Screen.Reservation.route)
                },
                modifier = Modifier.clip(RoundedCornerShape(20.dp))
                    .border(
                        width = 1.5.dp,
                        color = Color(94, 144, 255, 255),
                        shape = RoundedCornerShape(20.dp)
                    ),
                colors = ButtonDefaults.filledTonalButtonColors(
                    containerColor = Color.White
                )
            ) {
                Text(
                    text = "จองห้องพัก",
                    color = Color(94, 144, 255, 255),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "รูปภายในห้อง",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.align(alignment = Alignment.Start)
                .padding(horizontal = 25.dp, vertical = 10.dp)
        )

        examplePic.forEach{ image->
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier.width(352.dp).height(211.dp)
            )

            Spacer(Modifier.height(20.dp))
        }

        Spacer(Modifier.height(50.dp))
    }
}


