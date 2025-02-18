package com.src.kanchanaratplace.screen.owner

import android.util.Log
import android.widget.Toast
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.src.kanchanaratplace.api_util.getBill
import com.src.kanchanaratplace.api_util.getRoomUtility
import com.src.kanchanaratplace.component.BaseScaffold
import com.src.kanchanaratplace.component.FloorSelection
import com.src.kanchanaratplace.component.SampleActionScaffold
import com.src.kanchanaratplace.component.SampleScaffold
import com.src.kanchanaratplace.data.Bill
import com.src.kanchanaratplace.data.Rooms
import com.src.kanchanaratplace.navigation.Screen
import com.src.kanchanaratplace.status.RoomStatus

@Composable
fun BillsScaffold(navController: NavHostController){
    SampleActionScaffold(
        navController = navController,
        title = "แจ้งชำระค่าบริการ",
        icon = Icons.Default.Edit,
        onClick = {

        }
    ) {
        BillsScreen(navController)
    }
}

@Composable
fun BillsScreen(navController : NavHostController){
    val scrollState = rememberScrollState()
    var selectedFloor by  remember { mutableIntStateOf(1) }

    val roomList = remember { mutableStateListOf<Rooms>() }

    val context = LocalContext.current

    LaunchedEffect(selectedFloor) {
        getRoomUtility(
            floor = selectedFloor,
            onResponse = { response ->
                roomList.clear()
                roomList.addAll(response ?: emptyList())
            },
            onElse = {
                Toast.makeText(context,"เกิดข้อผิดพลาดในการโหลดข้อมูล",Toast.LENGTH_SHORT).show()
            },
            onFailure = { t->
                Toast.makeText(context,"Error Check LogCat",Toast.LENGTH_SHORT).show()
                t.message?.let { Log.e("Error", t.message!!) }
            }
        )
    }

    Column (
        modifier = Modifier.fillMaxSize()
            .verticalScroll(scrollState)
            .background(Color(240, 240, 240, 255)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        Row(
            modifier = Modifier.fillMaxWidth()
                .background(Color.White),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row (
                modifier = Modifier.fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = "เดือน/ปี",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = "กุมภาพันธ์/2568",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        FloorSelection(selectedFloor) { newFloor->
            selectedFloor = newFloor
        }


        roomList.forEach { room->
            var billData by remember { mutableStateOf<Bill?>(null) }
            getBill(
                room.roomId,
                2,
                2025,
                onResponse = { response->
                    billData = response
                },
                onElse = {
                    Toast.makeText(context,"เกิดข้อผิดพลาด",Toast.LENGTH_SHORT).show()
                },
                onFailure = { t->
                    t.message?.let { Log.e("Error", t.message!!) }
                }
            )
            Card (
                modifier = Modifier.fillMaxWidth()
                    .padding(10.dp),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ){
                Row (
                    modifier = Modifier.fillMaxSize()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Text(
                            text = "ห้อง",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        Text(
                            text = room.code,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Text(
                            text = "หน่วยน้ำที่ใช้",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(112, 110, 110, 255)
                        )

                        Text(
                            text = "${billData?.waterUsed ?: "-"}",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Text(
                            text = "ค่าไฟ",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(112, 110, 110, 255)
                        )

                        Text(
                            text = "${billData?.electricityUsed ?: "-"}",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Text(
                            text = "รวม (บาท)",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(112, 110, 110, 255)
                        )

                        Text(
                            text = "${billData?.totalPrice ?: "-"}",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Button(
                        onClick = {
                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                "bill_data",billData
                            )
                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                "room_data" , room
                            )
                            navController.navigate(Screen.BillEdit.route)
                        },
                        modifier = Modifier.width(72.dp).height(58.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if(room.status == RoomStatus.ROOM_OCCUPIED.message){ Color(172, 198, 255, 255)}
                                            else{Color(240, 240, 240, 255) },
                            contentColor = if(room.status == RoomStatus.ROOM_OCCUPIED.message){Color(94, 144, 255, 255)}
                                            else{Color(112, 110, 110, 255)
                            }
                        ),
                        shape = RoundedCornerShape(10.dp),
                        enabled = room.status == RoomStatus.ROOM_OCCUPIED.message
                    ) {
                        if(room.status == RoomStatus.ROOM_OCCUPIED.message){
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )
                        }else{
                            Text(
                                text = "ห้อง\n" +
                                        "ว่าง",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }
        }

    }
}