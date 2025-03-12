package com.src.kanchanaratplace.screen.member

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import com.src.kanchanaratplace.R
import com.src.kanchanaratplace.api_util.getLatestBillUtility
import com.src.kanchanaratplace.api_util.getOneRoomUtility
import com.src.kanchanaratplace.api_util.getReservationUtility
import com.src.kanchanaratplace.api_util.getRoomContractUtility
import com.src.kanchanaratplace.component.SampleScaffold
import com.src.kanchanaratplace.component.SmallWhiteBlueButton
import com.src.kanchanaratplace.data.Contract
import com.src.kanchanaratplace.data.DefaultRooms
import com.src.kanchanaratplace.data.Reservation
import com.src.kanchanaratplace.data_util.Receipt
import com.src.kanchanaratplace.navigation.Screen
import com.src.kanchanaratplace.session.MemberSharePreferencesManager

@Composable
fun MemberContractScaffold(navController: NavHostController){
    SampleScaffold(navController,"สัญญา") {
        MemberContractScreen(navController)
    }
}

@Composable
fun MemberContractScreen(navController : NavHostController){

    val scrollState = rememberScrollState()
    val context = LocalContext.current

    val sharePreferences = remember { MemberSharePreferencesManager(context) }

    var roomData by remember { mutableStateOf<DefaultRooms?>(null) }
    var contract by remember { mutableStateOf<Contract?>(null) }
    var reservation by remember { mutableStateOf<Reservation?>(null) }

    val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current
    val lifecycleState by lifecycleOwner.lifecycle.currentStateFlow.collectAsState()
    LaunchedEffect (lifecycleState){
        when(lifecycleState){
            Lifecycle.State.DESTROYED -> {}
            Lifecycle.State.INITIALIZED -> {}
            Lifecycle.State.CREATED -> {}
            Lifecycle.State.STARTED -> {}
            Lifecycle.State.RESUMED -> {
                getOneRoomUtility(
                    sharePreferences.member?.roomId !!,
                    onResponse = { response->
                        roomData = response
                    },
                    onElse = { responseError->
                        Toast.makeText(context,"Room data not found.",Toast.LENGTH_SHORT).show()
                        Log.e("Error",responseError.message())
                    },
                    onFailure = { t->
                        Toast.makeText(context,"Error onFailure",Toast.LENGTH_SHORT).show()
                        Log.e("Error",t.message ?: "No message")
                    }
                )

                getRoomContractUtility(
                    sharePreferences.member?.roomId !!,
                    onResponse = { response->
                        contract = response
                    },
                    onElse = { responseError->
                        Toast.makeText(context,"Contract data not found.",Toast.LENGTH_SHORT).show()
                        Log.e("Error",responseError.message())
                    },

                    onFailure = { t->
                        Toast.makeText(context,"Error onFailure",Toast.LENGTH_SHORT).show()
                        Log.e("Error",t.message ?: "No message")
                    }
                )

            }
        }
    }

    if(contract != null){
        getReservationUtility(
            contract!!.reservationId,
            onResponse = { response->
                reservation = response
            },
            onElse = { responseError ->
                Toast.makeText(context, "Reservation data not found.", Toast.LENGTH_SHORT).show()
                Log.e("Error",responseError.message())
            },
            onFailure = { t ->
                Toast.makeText(context, "Error onFailure", Toast.LENGTH_SHORT).show()
                Log.e("Error", t.message ?: "No message")
            }
        )
    }

    Column (
        modifier = Modifier.fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Spacer(modifier = Modifier.height(20.dp))

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(R.drawable.purple_house),
                contentDescription = null,
                modifier = Modifier
                    .width(84.dp)
                    .height(72.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column (
                horizontalAlignment = Alignment.Start
            ){
                Text(
                    text = "หอกาญจนารัตน์ เพลส",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = "ชั้น ${roomData?.roomFloor}",
                    fontSize = 16.sp
                )

                Text(
                    text = "ห้อง ${roomData?.roomCode}",
                    fontSize = 16.sp
                )
            }
        }

        Card (
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(
                width = 1.dp,
                color = Color.LightGray
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ){
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(25.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Image(
                        painter = painterResource(R.drawable.colorful_icon),
                        contentDescription = null,
                        modifier = Modifier.size(66.dp)
                    )

                    Spacer(modifier = Modifier.width(15.dp))

                    Column (
                        horizontalAlignment = Alignment.Start
                    ){
                        Text(
                            text = "คุณ ${sharePreferences.member?.name}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        Row (
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Text(
                                text = "เบอร์โทรศัพท์ ${sharePreferences.member?.phone}",
                                fontSize = 13.sp
                            )

                            Spacer(modifier = Modifier.width(5.dp))

                            Icon(
                                imageVector = Icons.Default.Phone,
                                contentDescription = null,
                                modifier = Modifier.size(23.dp)
                            )
                        }
                    }
                }
            }

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 25.dp),
                horizontalArrangement = Arrangement.Start
            ){
                Text(
                    text = "รายละเอียดการจอง",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            HorizontalDivider(thickness = 0.5.dp)

            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 25.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ){
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = "เงินมัดจำ/ประกัน",
                        fontSize = 16.sp
                    )

                    Text(
                        text = "1,000 บาท",
                        fontSize = 16.sp
                    )
                }

                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = "รวมทั้งหมด",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Text(
                        text = "1,000 บาท",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            text = "ชำระแล้ว",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            color = Color(50, 161, 41, 255)
                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        Icon(
                            painter = painterResource(R.drawable.check_green),
                            contentDescription = null,
                            modifier = Modifier.size(18.dp),
                            tint = Color(20, 174, 92, 255)
                        )
                    }

                    SmallWhiteBlueButton(
                        text = "พิมพ์ใบเสร็จ",
                        onClick = {
                            val receiptData = listOf<Receipt>(
                                Receipt("ค่าจองห้อง",1000),
                            )

                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                "name",reservation?.name
                            )

                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                "receipt_data",receiptData
                            )
                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                "total_price",1000
                            )

                            navController.navigate(Screen.Receipt.route)
                        }
                    )
                }
            }

            HorizontalDivider(thickness = 0.5.dp ,)

            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 25.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ){
                Row(
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text(
                        text = "รายละเอียดการจอง",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            text = "กรอกแล้ว",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            color = Color(50, 161, 41, 255)
                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        Icon(
                            painter = painterResource(R.drawable.check_green),
                            contentDescription = null,
                            modifier = Modifier.size(18.dp),
                            tint = Color(20, 174, 92, 255)
                        )
                    }

                    SmallWhiteBlueButton(
                        text = "พิมพ์ใบทำสัญญา",
                        onClick = {
                            navController.navigate(Screen.ContractPaperMember.route)
                        }
                    )
                }
            }

            HorizontalDivider(thickness = 0.5.dp , color = Color.LightGray)
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 25.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ){
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = "ค่าบริการ",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = "ราคาห้อง",
                        fontSize = 16.sp,
                    )

                    Text(
                        text = "4,000 บาท",
                        fontSize = 16.sp,
                    )
                }

                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = "เงินมัดจำส่วนที่เหลือ",
                        fontSize = 16.sp,
                    )

                    Text(
                        text = "3,000 บาท",
                        fontSize = 16.sp,
                    )
                }

                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = "จ่ายล่วงหน้า",
                        fontSize = 16.sp,
                    )

                    Text(
                        text = "4,000 บาท",
                        fontSize = 16.sp,
                    )
                }

                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = "รวมทั้งหมด",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Text(
                        text = "11,000 บาท",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            text = "ชำระแล้ว",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            color = Color(50, 161, 41, 255)
                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        Icon(
                            painter = painterResource(R.drawable.check_green),
                            contentDescription = null,
                            modifier = Modifier.size(18.dp),
                            tint = Color(20, 174, 92, 255)
                        )
                    }

                    SmallWhiteBlueButton(
                        text = "พิมพ์ใบเสร็จ",
                        onClick = {
                            val receiptData = listOf<Receipt>(
                                Receipt("ราคาห้อง",4000),
                                Receipt("เงินมัดจำส่วนที่เหลือ",3000),
                                Receipt("จ่ายล่วงหน้า",4000),
                            )

                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                "name" , reservation?.name
                            )

                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                "receipt_data",receiptData
                            )

                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                "total_price",11000
                            )

                            navController.navigate(Screen.Receipt.route)
                        }
                    )
                }
            }

        }

    }
}