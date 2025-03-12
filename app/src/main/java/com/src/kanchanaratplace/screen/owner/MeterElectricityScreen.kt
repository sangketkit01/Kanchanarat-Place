package com.src.kanchanaratplace.screen.owner

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
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
import com.src.kanchanaratplace.api_util.createElectricityBillUtility
import com.src.kanchanaratplace.api_util.createWaterBillUtility
import com.src.kanchanaratplace.api_util.getBillUtility
import com.src.kanchanaratplace.api_util.getRoomUtility
import com.src.kanchanaratplace.component.FloorSelection
import com.src.kanchanaratplace.component.MyDatePicker
import com.src.kanchanaratplace.component.SampleScaffold
import com.src.kanchanaratplace.data.Bill
import com.src.kanchanaratplace.data.Rooms
import java.time.LocalDate
import java.util.Calendar

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MeterElectricityScaffold(navController: NavHostController){
    SampleScaffold(navController,"มิเตอร์ไฟฟ้า"){
        MeterElectricityScreen(navController)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MeterElectricityScreen(navController : NavHostController){
    var selectedFloor by remember { mutableIntStateOf(1) }

    val currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1
    val currentYear = Calendar.getInstance().get(Calendar.YEAR)

    var selectedMonthIndex by remember { mutableIntStateOf(currentMonth) }
    var selectedYearCE by remember { mutableIntStateOf(currentYear) }

    val roomList = remember { mutableStateListOf<Rooms>() }

    val context = LocalContext.current

    var edit by remember { mutableStateOf(false) }
    var save by remember { mutableStateOf(false) }

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
    ) {
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

        if (!edit) {
            Button(
                onClick = {
                    edit = true
                },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = Color(0xFF5E90FF)
                ),
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 15.dp)
            ) {
                Text(
                    text = "แก้ไข",
                    fontWeight = FontWeight.Bold
                )
            }
        } else {
            Button(
                onClick = {
                    save = true
                    edit = false
                },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = Color(0xFF5E90FF)
                ),
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 15.dp)
            ) {
                Text(
                    text = "บันทึก",
                    fontWeight = FontWeight.Bold
                )
            }
        }
        var savedRoomsCount = remember { mutableIntStateOf(0) }

        roomList.forEach { room ->
            val billData = billDataMap[room.roomId]
            RoomElectricityMeterCard(
                room = room,
                billData = billData,
                edit = edit,
                save = save,
                onSaved = {
                    savedRoomsCount.intValue++
                    if (savedRoomsCount.intValue >= roomList.size) {
                        save = false
                        savedRoomsCount.intValue = 0
                    }
                }
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RoomElectricityMeterCard(
    room: Rooms,
    billData: Bill?,
    edit: Boolean,
    save: Boolean,
    onSaved: () -> Unit
) {
    var previousMeter by remember(billData) { mutableIntStateOf(billData?.previousElectricityUsed ?: 0) }
    var currentMeter by remember(billData) { mutableIntStateOf(billData?.currentElectricityUsed ?: 0) }
    val totalUsed by remember(previousMeter, currentMeter) { derivedStateOf { currentMeter - previousMeter } }

    val context = LocalContext.current

    LaunchedEffect(save) {
        if (save && room.status == "ไม่ว่าง") {
            createElectricityBillUtility(
                room.roomId,
                previousMeter,
                currentMeter,
                totalUsed,
                LocalDate.now().toString(),
                onResponse = {
                    Log.d("Bill", "Successfully created electricity bill for room ${room.roomId}")
                    onSaved()
                },
                onElse = { elseResponse ->
                    Toast.makeText(context, "Insert bill failed for room ${room.code}", Toast.LENGTH_SHORT).show()
                    Log.e("Error", elseResponse.message())
                    onSaved()
                },
                onFailure = { t ->
                    Toast.makeText(context, "Error onFailure for room ${room.code}", Toast.LENGTH_SHORT).show()
                    Log.e("Error", t.message ?: "No message")
                    onSaved()
                }
            )
        } else if (save) {
            onSaved()
        }
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
                    text = "จดครั้งก่อน",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(112, 110, 110, 255)
                )

                if (edit && room.status == "ไม่ว่าง") {
                    OutlinedTextField(
                        value = previousMeter.toString(),
                        onValueChange = { newValue ->
                            previousMeter = newValue.toIntOrNull() ?: 0
                        },
                        modifier = Modifier.width(65.dp)
                    )
                } else {
                    Text(
                        text = "${if (previousMeter > 0) previousMeter else "-"}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "ปัจจุบัน",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(112, 110, 110, 255)
                )

                if (edit && room.status == "ไม่ว่าง") {
                    OutlinedTextField(
                        value = currentMeter.toString(),
                        onValueChange = { newValue ->
                            currentMeter = newValue.toIntOrNull() ?: 0
                        },
                        modifier = Modifier.width(65.dp)
                    )
                } else {
                    Text(
                        text = "${if (currentMeter > 0) currentMeter else "-"}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "หน่วย",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(112, 110, 110, 255)
                )

                if (edit && room.status == "ไม่ว่าง") {
                    OutlinedTextField(
                        value = totalUsed.toString(),
                        onValueChange = { },
                        modifier = Modifier.width(65.dp),
                        readOnly = true
                    )
                } else {
                    Text(
                        text = "${if (totalUsed > 0) totalUsed else "-"}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
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
                    text = "ห้อง${room.status}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}