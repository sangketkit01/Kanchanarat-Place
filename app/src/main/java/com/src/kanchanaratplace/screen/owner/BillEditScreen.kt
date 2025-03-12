package com.src.kanchanaratplace.screen.owner

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.src.kanchanaratplace.api_util.createBillUtility
import com.src.kanchanaratplace.api_util.getOneRoomUtility
import com.src.kanchanaratplace.component.SampleScaffold
import com.src.kanchanaratplace.data.Bill
import com.src.kanchanaratplace.data.BillRequest
import com.src.kanchanaratplace.data.DefaultRooms
import com.src.kanchanaratplace.data.Rooms
import com.src.kanchanaratplace.session.MemberSharePreferencesManager
import com.src.kanchanaratplace.status.OtherStatus
import java.sql.Date
import java.time.LocalDate
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BillEditScaffold(navController: NavHostController){
    val roomData = navController.previousBackStackEntry?.savedStateHandle?.get<Rooms>("room_data")
    SampleScaffold(navController,"ห้อง ${roomData?.roomId}") {
        BillEditScreen(navController)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BillEditScreen(navController : NavHostController){
    val scrollState = rememberScrollState()

    val billData = navController.previousBackStackEntry?.savedStateHandle?.get<Bill>("bill_data")

    var waterUsed by remember { mutableStateOf(billData?.waterUsed?.toString() ?: "") }
    var electricalUsed by remember { mutableStateOf(billData?.electricityUsed?.toString() ?: "") }
    var waterBill by remember { mutableIntStateOf(150) }
    var electricalBill by remember { mutableIntStateOf(0) }

    val context = LocalContext.current

    val room = navController.previousBackStackEntry?.savedStateHandle?.get<Rooms>("room_data")
    var roomData by remember { mutableStateOf<DefaultRooms?>(null) }
    room?.roomId?.let {
        getOneRoomUtility(
        roomId = it,
        onResponse = { response->
            roomData = response
        },
        onElse = {
            Toast.makeText(context,"ไม่พบข้อมูล",Toast.LENGTH_SHORT).show()
        },
        onFailure = { t->
            Toast.makeText(context,"Error Check LogCat",Toast.LENGTH_SHORT).show()
            t.message?.let { Log.e("Error", t.message!!) }
        }
    )
    }

    LaunchedEffect(waterUsed, electricalUsed) {
        val waterUsage = waterUsed.toIntOrNull() ?: 0
        waterBill = if (waterUsage > 5) 150 + ((waterUsage - 5) * 20) else 150

        electricalBill = (electricalUsed.toIntOrNull() ?: 0) * 8
    }

    Column (
        modifier = Modifier.fillMaxSize()
            .verticalScroll(scrollState)
            .background(Color(240, 240, 240, 255)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        Card (
            modifier = Modifier.fillMaxWidth()
                .padding(10.dp),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ){
            Column (
                modifier = Modifier.fillMaxWidth()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Row (
                    modifier = Modifier.fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(240, 240, 240, 255))
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Spacer(modifier = Modifier.weight(2f))

                    Text(
                        text = "หน่วยที่ใช้",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f),
                        color = Color(112, 110, 110, 255)
                    )

                    Text(
                        text = "จำนวนเงิน",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f),
                        color = Color(112, 110, 110, 255)
                    )
                }

                Row (
                    modifier = Modifier.fillMaxWidth().padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = "ค่าห้องพัก",
                        fontSize = 16.sp,
                        modifier = Modifier.weight(3f)
                    )

                    Text(
                        text = "4,000 บาท",
                        fontSize = 16.sp,
                        modifier = Modifier.weight(1f)
                    )
                }

                Row (
                    modifier = Modifier.fillMaxWidth().padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = "ค่าน้ำ",
                        fontSize = 16.sp,
                        modifier = Modifier.weight(2f)
                    )

                    OutlinedTextField(
                        value = waterUsed,
                        onValueChange = {newValue->
                            waterUsed = newValue
                            val waterUsage = newValue.toIntOrNull() ?: 0
                            waterBill = if (waterUsage > 5) {
                                150 + ((waterUsage - 5) * 20)
                            } else 150
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(4.dp),
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White
                        )
                    )

                    Text(
                        text = "$waterBill บาท"
                        ,
                        fontSize = 16.sp,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                }

                Row (
                    modifier = Modifier.fillMaxWidth().padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = "ค่าไฟ",
                        fontSize = 16.sp,
                        modifier = Modifier.weight(2f)
                    )

                    OutlinedTextField(
                        value = electricalUsed,
                        onValueChange = {newValue->
                            electricalUsed = newValue
                            electricalBill = (newValue.toIntOrNull() ?: 0) * 8
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(4.dp),
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White
                        )
                    )

                    Text(
                        text = "$electricalBill บาท",
                        fontSize = 16.sp,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                }

                Row (
                    modifier = Modifier.fillMaxWidth().padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ){
                    Text(
                        text = "รวมทั้งหมด: ${(4000 + waterBill + electricalBill).toString().format("%,d")} บาท",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Column (
                    modifier = Modifier.fillMaxWidth()
                        .padding(10.dp),
                    horizontalAlignment = Alignment.Start
                ){
                    Text(
                        text = "หมายเหตุ: \n" +
                                "ค่าไฟหน่วยละ 8 บาท\n" +
                                "ค่าน้ำ 5 หน่วยแรกเหมาจ่าย 150 บาท\n" +
                                "ถ้าใช้เกินคิดเพิ่มหน่วยละ 20 บาท",
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        color = Color(165, 165, 165, 255)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = {
                        roomData?.roomId?.let {
                            val bill = BillRequest(
                                it,
                                waterUsed.toFloatOrNull() ?: 0f,
                                electricalUsed.toFloatOrNull() ?: 0f,
                                (4000+waterBill+electricalBill).toFloat(),
                                LocalDate.now().toString(),
                                OtherStatus.PENDING.id
                            )
                            createBillUtility(
                                bill,
                                onResponse = {
                                    Toast.makeText(context,"ส่งใบบิลสำเร็จ", Toast.LENGTH_SHORT).show()
                                    navController.popBackStack()
                                },
                                onElse = { response->
                                    val errorBody = response.errorBody()?.string()
                                    Log.e("API Response", "Error Code: ${response.code()} | Message: ${response.message()} | Body: $errorBody")
                                    Toast.makeText(context, "ส่งใบบิลล้มเหลว: $errorBody", Toast.LENGTH_SHORT).show()
                                    Toast.makeText(context,"ส่งใบบิลล้มเหลว", Toast.LENGTH_SHORT).show()
                                },
                                onFailure = {t->
                                    Toast.makeText(context,"Error Check LogCat", Toast.LENGTH_SHORT).show()
                                    t.message?.let { Log.e("Error",it) }
                                }
                            )
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(94, 144, 255, 255),
                        contentColor = Color.White
                    ),
                    modifier = Modifier.width(163.dp).height(39.dp)
                ) {
                    Text(
                        text = "ส่งใบบิล",
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}