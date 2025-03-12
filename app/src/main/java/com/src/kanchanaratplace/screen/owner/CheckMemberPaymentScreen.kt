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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.src.kanchanaratplace.api_util.getBillUtility
import com.src.kanchanaratplace.api_util.getMemberByRoomUtility
import com.src.kanchanaratplace.api_util.getRoomUtility
import com.src.kanchanaratplace.component.FloorSelection
import com.src.kanchanaratplace.component.MyDatePicker
import com.src.kanchanaratplace.component.SampleScaffold
import com.src.kanchanaratplace.data.Bill
import com.src.kanchanaratplace.data.Member
import com.src.kanchanaratplace.data.Rooms
import com.src.kanchanaratplace.navigation.Screen
import com.src.kanchanaratplace.status.OtherStatus
import java.util.Calendar

@Composable
fun CheckMemberPaymentScaffold(navController: NavHostController){
    SampleScaffold(navController,"การชำระเงิน"){
        CheckMemberPaymentScreen(navController)
    }
}

@Composable
fun CheckMemberPaymentScreen(navController : NavHostController){
    var selectedFloor by remember { mutableIntStateOf(1) }

    val currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1
    val currentYear = Calendar.getInstance().get(Calendar.YEAR)

    var selectedMonthIndex by remember { mutableIntStateOf(currentMonth) }
    var selectedYearCE by remember { mutableIntStateOf(currentYear) }

    val roomList = remember { mutableStateListOf<Rooms>() }

    val context = LocalContext.current

    val billDataMap = remember { mutableStateMapOf<Int, Bill?>() }

    LaunchedEffect(selectedFloor) {
        getRoomUtility(
            floor = selectedFloor,
            onResponse = { response ->
                roomList.clear()
                roomList.addAll(response ?: emptyList())
                roomList.forEach { room ->
                    getBillUtility(
                        room.roomId,
                        selectedMonthIndex,
                        selectedYearCE,
                        onResponse = { response ->
                            billDataMap[room.roomId] = response
                        },
                        onElse = {
                            billDataMap[room.roomId] = null
                            Toast.makeText(context, "เกิดข้อผิดพลาด", Toast.LENGTH_SHORT).show()
                        },
                        onFailure = { t ->
                            t.message?.let { Log.e("Error", it) }
                        }
                    )
                }
            },
            onElse = {
                Toast.makeText(context, "เกิดข้อผิดพลาดในการโหลดข้อมูล", Toast.LENGTH_SHORT).show()
            },
            onFailure = { t ->
                Toast.makeText(context, "Error Check LogCat", Toast.LENGTH_SHORT).show()
                t.message?.let { Log.e("Error", t.message!!) }
            }
        )
    }

    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(Color(240, 240, 240, 255)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                MyDatePicker(
                    selectedMonthIndex = selectedMonthIndex,
                    selectedYearCE = selectedYearCE,
                    onMonthSelected = { selectedMonthIndex = it },
                    onYearSelected = { selectedYearCE = it }
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        FloorSelection(selectedFloor) { newFloor ->
            selectedFloor = newFloor
        }

        val triggerReload by remember { derivedStateOf { selectedMonthIndex to selectedYearCE } }

        LaunchedEffect(triggerReload) {
            billDataMap.clear()
            roomList.forEach { room ->
                getBillUtility(
                    room.roomId,
                    selectedMonthIndex,
                    selectedYearCE,
                    onResponse = { response ->
                        billDataMap[room.roomId] = response
                    },
                    onElse = {
                        billDataMap[room.roomId] = null
                        Toast.makeText(context, "เกิดข้อผิดพลาด", Toast.LENGTH_SHORT).show()
                    },
                    onFailure = { t ->
                        t.message?.let { Log.e("Error", it) }
                    }
                )
            }
        }

        roomList.forEach { room->
            val billData = billDataMap[room.roomId]
            var member by remember { mutableStateOf<Member?>(null) }

            if(room.status == "ไม่ว่าง"){
                getMemberByRoomUtility(
                    room.roomId,
                    onResponse = { response->
                        member = response
                    },
                    onElse = { elseResponse->
                        Toast.makeText(context,"Cannot fetch member data",Toast.LENGTH_SHORT).show()
                        Log.e("Error",elseResponse.message())
                    },
                    onFailure = { t->
                        Toast.makeText(context,"Error onFailure",Toast.LENGTH_SHORT).show()
                        Log.e("Error",t.message ?: "No message")
                    }
                )
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
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
                    ) {
                        Text(
                            text = "ชื่อผู้เช่า",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(112, 110, 110, 255)
                        )

                        Text(
                            text = member?.name?.split(" ")?.get(0) ?: "Unknow",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )

                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "ค่าใช้จ่าย",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(112, 110, 110, 255)
                        )

                        Text(
                            text = (billData?.totalPrice ?: 0).toString(),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )

                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "สถานะ",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(112, 110, 110, 255)
                        )

                        Text(
                            text = if(billData?.statusId == OtherStatus.SUCCESS.id) "ชำระแล้ว"
                                    else if(room.status != "ไม่ว่าง") "ห้องว่าง"
                                    else if(billData?.slipPath != null) "รอตรวจสอบ"
                                    else "ยังไม่ชำระ",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = if(billData?.statusId == OtherStatus.SUCCESS.id) Color(0xFF32A129)
                                    else if(room.status != "ไม่ว่าง") Color.Black
                                    else if(billData?.slipPath != null) Color(0xFFFFCD29)
                                    else Color(0xFFFF8000)
                        )
                    }
                }

                Button(
                    onClick = {
                        navController.currentBackStackEntry?.savedStateHandle?.set(
                            "room_data",room
                        )

                        navController.currentBackStackEntry?.savedStateHandle?.set(
                            "bill_data" , billData
                        )

                        navController.currentBackStackEntry?.savedStateHandle?.set(
                            "member_data",member
                        )
                        navController.navigate(Screen.CheckMemberPaymentDetail.route)
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF5E90FF),
                        contentColor = Color.White
                    ),
                    modifier = Modifier.padding(10.dp)
                        .align(Alignment.End),
                    enabled = room.status == "ไม่ว่าง"

                ){
                    Text("ดูรายละเอียด")
                }
            }
        }
    }
}