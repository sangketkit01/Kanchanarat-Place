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
    var billData by remember { mutableStateOf<Bill?>(null) }

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
                            painter = painterResource(R.drawable.red_dot),
                            contentScale = ContentScale.Fit,
                            contentDescription = null,
                            modifier = Modifier.size(14.dp)
                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        Text(
                            text = "ค้างชำระ",
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
                    fontSize = 16.sp,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "เดือน มกราคม 2025",
                    fontSize = 14.sp,
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
                        fontSize = 14.sp,
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
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Start
                    )

                    Text(
                        text = "4,000",
                        fontSize = 14.sp,
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
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = "จำนวนเงิน",
                        fontSize = 14.sp,
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
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Start
                    )

                    Text(
                        text = "12",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = "96",
                        fontSize = 14.sp,
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
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Start
                    )

                    Text(
                        text = "4",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = "150",
                        fontSize = 14.sp,
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
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Start
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = "4,246",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.End
                    )
                }

                HorizontalDivider(thickness = 1.dp)

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        navController.navigate(Screen.MemberCheckBill.route)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(94, 144, 255, 255),
                        contentColor = Color.White
                    ),
                    modifier = Modifier.width(226.dp).height(39.dp)
                ) {
                    Text(
                        text = "ชำระเงิน",
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))

                Row(
                    modifier = Modifier.fillMaxWidth()
                        .background(Color(217, 217, 217, 255))
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "หมายเหตุ",
                        fontSize = 14.sp,
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
                        fontSize = 12.sp,
                        color = Color(165, 165, 165, 255)
                    )
                }

            }
        }
    }
}