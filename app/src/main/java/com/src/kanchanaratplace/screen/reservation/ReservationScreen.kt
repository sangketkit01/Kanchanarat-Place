package com.src.kanchanaratplace.screen.reservation

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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.src.kanchanaratplace.component.SmallWhiteBlueButton
import com.src.kanchanaratplace.api.RoomAPI
import com.src.kanchanaratplace.component.BaseScaffold
import com.src.kanchanaratplace.data.Rooms
import com.src.kanchanaratplace.navigation.Screen
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class RoomStatus(
    val number: String,
    val status: String,
    val color: Color
)

@Composable
fun ReservationScaffold(navController: NavHostController){
    BaseScaffold(navController) {
        ReservationScreen(navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReservationScreen(navController: NavHostController) {
    var selectedFloor by remember { mutableIntStateOf(1) }
    var selectedRoom by remember { mutableStateOf("") }

    val roomClient = RoomAPI.create()
    val roomList = remember { mutableStateListOf<Rooms>() }
    var loading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(selectedFloor) {
        loading = true
        error = null
        roomClient.getRoom(selectedFloor).enqueue(object : Callback<List<Rooms>> {
            override fun onResponse(call: Call<List<Rooms>>, response: Response<List<Rooms>>) {
                if (response.isSuccessful) {
                    roomList.clear()
                    roomList.addAll(response.body() ?: emptyList())
                } else {
                    error = "เกิดข้อผิดพลาดในการโหลดข้อมูล (${response.code()})"
                }
                loading = false
            }

            override fun onFailure(call: Call<List<Rooms>>, t: Throwable) {
                error = "ไม่สามารถเชื่อมต่อ API ได้: ${t.message}"
                loading = false
            }
        })
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 5.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            IconButton(
                onClick = {
                    navController.navigate(Screen.AvailableRoom.route)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null
                )
            }


            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = "ห้องว่าง",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Row (
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ){
            SmallWhiteBlueButton(
                text = "ตรวจสอบการจองห้อง",
                onClick = {
                    navController.navigate(Screen.CheckReservation.route)
                }
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp)
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

        Spacer(modifier = Modifier.height(20.dp))

        when {
            loading -> Text("กำลังโหลดข้อมูล...", fontSize = 18.sp)
            error != null -> Text(error!!, color = Color.Red, fontSize = 18.sp)
            else -> LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp),
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 16.dp,
                    bottom = 40.dp
                )
            ) {
                items(roomList) { room ->
                    val roomColor = when (room.status) {
                        com.src.kanchanaratplace.status.RoomStatus.ROOM_AVAILABLE.message -> Color(50, 161, 41, 255)
                        com.src.kanchanaratplace.status.RoomStatus.ROOM_OCCUPIED.message  -> Color(251, 35, 35, 255)
                        com.src.kanchanaratplace.status.RoomStatus.ROOM_RESERVED.message  -> Color(217, 217, 217, 255)
                        else -> Color.Gray
                    }
                    RoomStatusItem(
                        roomId = room.roomId,
                        room = RoomStatus(
                            number = room.code,
                            status = room.status,
                            color = roomColor
                        ),
                        selectedRoom = selectedRoom,
                        onSelected = { selectedRoom = it },
                        navController = navController
                    )
                }
            }
        }
    }
}


@Composable
fun RoomStatusItem(roomId : Int,room: RoomStatus, selectedRoom : String, onSelected:(String)->Unit,
                   navController: NavHostController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = room.number,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(94, 144, 255, 255),
            modifier = Modifier.border(
                width = 1.5.dp,
                color = Color(94, 144, 255, 255),
                shape = RoundedCornerShape(50.dp)
            ).padding(10.dp)
        )

        Button(
            onClick = {
                onSelected(room.number)
                navController.currentBackStackEntry?.savedStateHandle?.set(
                    "room",room.number
                )
                navController.currentBackStackEntry?.savedStateHandle?.set(
                    "room_id",roomId
                )

                navController.navigate(Screen.MakeReservation.route)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = room.color,
                disabledContainerColor = room.color
            ),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .width(104.dp)
                .height(102.dp),
            enabled = room.status == "ว่าง"
        ) {
            Text(
                text = room.status,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
        }

    }
}