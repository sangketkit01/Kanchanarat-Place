package com.src.kanchanaratplace.screen.member

import android.util.Log
import android.widget.Toast
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import com.src.kanchanaratplace.R
import com.src.kanchanaratplace.api_util.getOneRoomUtility
import com.src.kanchanaratplace.component.SampleScaffold
import com.src.kanchanaratplace.data.Bill
import com.src.kanchanaratplace.data.DefaultRooms
import com.src.kanchanaratplace.navigation.Screen
import com.src.kanchanaratplace.session.MemberSharePreferencesManager
import com.src.kanchanaratplace.status.OtherStatus

@Composable
fun MemberBillDetailScaffold(navController: NavHostController){
    SampleScaffold(navController,"บิลค่าเช่า") {
        MemberBillDetailScreen(navController)
    }
}

@Composable
fun MemberBillDetailScreen(navController : NavHostController){
    val context = LocalContext.current
    val sharePreferences = remember { MemberSharePreferencesManager(context) }

    var roomData by remember { mutableStateOf<DefaultRooms?>(null) }
    var billData = navController.previousBackStackEntry?.savedStateHandle?.get<Bill>("bill_data")

    var status by remember { mutableStateOf("") }
    var dot by remember { mutableIntStateOf(R.drawable.red_dot) }

    var waterBill by remember { mutableIntStateOf(150) }
    var electricalBill by remember { mutableIntStateOf(0) }

    val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current
    val lifecycleState by lifecycleOwner.lifecycle.currentStateFlow.collectAsState()
    LaunchedEffect (lifecycleState){
        when(lifecycleState){
            Lifecycle.State.DESTROYED -> {}
            Lifecycle.State.INITIALIZED -> {}
            Lifecycle.State.CREATED -> {}
            Lifecycle.State.STARTED -> {}
            Lifecycle.State.RESUMED -> {
                sharePreferences.member?.roomId?.let {
                    getOneRoomUtility(
                        roomId = it,
                        onResponse = { response->
                            roomData = response
                        },
                        onElse = {
                            Toast.makeText(context,"Data not found", Toast.LENGTH_SHORT).show()
                        },
                        onFailure = { t->
                            Toast.makeText(context,"Error Check LogCat", Toast.LENGTH_SHORT).show()
                            t.message?.let { Log.e("Error",it) }
                        }
                    )

                    val waterUsage = billData?.waterUsed ?: 0
                    waterBill = if (waterUsage > 5) 150 + ((waterUsage - 5) * 20) else 150
                    electricalBill = (billData?.electricityUsed ?: 0) * 8

                    when(billData?.statusId){
                        OtherStatus.PENDING.code -> {
                            status = "ค้างชำระ"
                        }
                        OtherStatus.EXPIRED.code -> {
                            status = "เลยกำหนดเวลา"
                        }
                        OtherStatus.SUCCESS.code -> {
                            status = "ชำระแล้ว"
                            dot = R.drawable.green_dot
                        }
                    }
                }
            }
        }
    }

    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(
                color = Color.White
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        Column (
            modifier = Modifier.fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "กาญจนารัตน์ เพลส",
                fontSize = 16.sp,
                fontWeight = FontWeight.ExtraBold
            )

            Row (
                modifier = Modifier.fillMaxWidth()
                    .padding(10.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(172, 198, 255, 255)),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "ห้อง ${roomData?.roomCode}",
                    fontSize = 27.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(10.dp)
                )
            }

            Column (
                modifier = Modifier.fillMaxWidth()
            ){
                Row (
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 15.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "สถานะบิล",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.ExtraBold
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = dot),
                            contentScale = ContentScale.Fit,
                            contentDescription = null,
                            modifier = Modifier.size(14.dp)
                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        Text(
                            text = status,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                }

                HorizontalDivider(thickness = 1.dp)
            }

            Column(
                modifier = Modifier.fillMaxWidth()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "บิลค่าเช่าห้อง",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "เดือน กุมภาพันธ์ 2025",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth()
                        .background(Color(217, 217, 217, 255))
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "รายการ",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "ค่าเช่าห้อง",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Start
                    )

                    Text(
                        text = "4,000",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.End
                    )
                }

                HorizontalDivider(thickness = 1.dp)

                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = "หน่วยที่ใช้",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = "จำนวนเงิน",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.End
                    )
                }

                HorizontalDivider(thickness = 1.dp)

                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "ค่าไฟ",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Start
                    )

                    Text(
                        text = "${billData?.electricityUsed ?: 0}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = "$electricalBill",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.End
                    )
                }

                HorizontalDivider(thickness = 1.dp)

                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "ค่าน้ำ",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Start
                    )

                    Text(
                        text = "${billData?.waterUsed ?: 0}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = "$waterBill",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.End
                    )
                }

                HorizontalDivider(thickness = 1.dp)

                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "รวม",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Start
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = "${billData?.totalPrice ?: 4150}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.End
                    )
                }

                HorizontalDivider(thickness = 1.dp)

                Spacer(modifier = Modifier.height(20.dp))

                val isPaid = billData?.statusId == OtherStatus.SUCCESS.code
                val buttonText = if (isPaid) "ชำระแล้ว" else "ชำระเงิน"
                val buttonColor = if (isPaid) Color(200, 200, 200, 255) else Color(94, 144, 255, 255)
                val isButtonEnabled = !isPaid

                Button(
                    onClick = {
                        if (!isPaid) {
                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                "bill_data",billData
                            )
                            navController.navigate(Screen.MemberCheckBill.route)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = buttonColor,
                        contentColor = Color.White
                    ),
                    modifier = Modifier.width(226.dp).height(39.dp),
                    enabled = isButtonEnabled
                ) {
                    Text(
                        text = buttonText,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp
                    )
                }


                Spacer(modifier = Modifier.height(30.dp))

                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .background(Color(217, 217, 217, 255))
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "หมายเหตุ",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                }

                Column (
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 10.dp)
                ){
                    Text(
                        text = "ค่าไฟหน่วยละ 8 บาท\n" +
                                "ค่าน้ำ 5 หน่วยแรกเหมาจ่าย 150 บาท\n" +
                                "ถ้าใช้เกินคิดเพิ่มหน่วยละ 20 บาท",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(165, 165, 165, 255)
                    )
                }

            }
        }
    }
}