package com.src.kanchanaratplace.screen.owner

import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavHostController
import com.src.kanchanaratplace.R
import com.src.kanchanaratplace.api_util.approveContractUtility
import com.src.kanchanaratplace.api_util.approveReservationUtility
import com.src.kanchanaratplace.api_util.makeRoomUnavailableUtility
import com.src.kanchanaratplace.component.SampleScaffold
import com.src.kanchanaratplace.component.SlipImageDialog
import com.src.kanchanaratplace.component.SmallWhiteBlueButton
import com.src.kanchanaratplace.data.Contract
import com.src.kanchanaratplace.data.DefaultRooms
import com.src.kanchanaratplace.data.Reservation
import com.src.kanchanaratplace.navigation.Screen

@Composable
fun ContractDetailScaffold(navController: NavHostController){
    SampleScaffold(navController,"ยืนยันการทำสัญญา") {
        ContractDetailScreen(navController)
    }
}


@Composable
fun ContractDetailScreen(navController : NavHostController){
    val contractData = navController.previousBackStackEntry?.savedStateHandle?.get<Contract>("contract_data")
    val roomData = navController.previousBackStackEntry?.savedStateHandle?.get<DefaultRooms>("room_data")
    val reservationData = navController.previousBackStackEntry?.savedStateHandle?.get<Reservation>("reservation_data")

    var contractSlipAlert by remember { mutableStateOf(false) }
    var feeSlipAlert by remember { mutableStateOf(false) }
    var approveAlert by remember { mutableStateOf(false) }
    var contractAlert by remember { mutableStateOf(false) }

    val context = LocalContext.current

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
                            text = "คุณ ${reservationData?.name}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        Row (
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Text(
                                text = "เบอร์โทรศัพท์ ${reservationData?.phone}",
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

            HorizontalDivider()

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
                        text = "ดูสลิป",
                        onClick = {
                            feeSlipAlert = true
                        }
                    )
                }
            }

            HorizontalDivider()

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
                        text = "ใบทำสัญญา",
                        fontSize = 16.sp
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
                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                "contract_path",contractData?.contractPath
                            )

                            navController.navigate(Screen.ContractDetailPaper.route)
                        }
                    )
                }
            }

            HorizontalDivider()
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
                        text = "ดูสลิป",
                        onClick = {
                            contractSlipAlert = true
                        }
                    )
                }
            }

        }

        Spacer(modifier = Modifier.height(10.dp))

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            FilledTonalButton(
                onClick = {

                },
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .border(
                        width = 1.dp,
                        color = Color(94, 144, 255, 255),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .width(163.dp)
                    .height(39.dp),
                colors = ButtonDefaults.filledTonalButtonColors(
                    containerColor = Color.White
                )
            ) {
                Text(
                    text = "ย้อนกลับ",
                    color = Color(94, 144, 255, 255),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            FilledTonalButton(
                onClick = {
                    approveAlert = true
                },
                colors = ButtonDefaults.filledTonalButtonColors(
                    containerColor = Color(94, 144, 255, 255)
                ),
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .border(
                        width = 1.5.dp,
                        color = Color.Transparent,
                        shape = RoundedCornerShape(50.dp)
                    ).height(39.dp)
            ) {
                Text(
                    text = "ยืนยันการทำสัญญา",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        }
    }

    if(feeSlipAlert){
        SlipImageDialog(reservationData?.slipPath) {
            feeSlipAlert = false
        }
    }

    if(contractSlipAlert){
        SlipImageDialog(contractData?.slipPath) {
            contractSlipAlert = false
        }
    }

    if (approveAlert){
        AlertDialog(
            onDismissRequest = {approveAlert = false},
            title = { Text("ยืนยันการอนุุมัติ") },
            text = { Text("คุณต้องการจะอนุมัติสัญญาของคุณ ${reservationData?.name} หรือไม่?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        contractData?.contractId?.let {
                            approveContractUtility(
                                contractId = it,
                                onResponse = {
                                    makeRoomUnavailableUtility(
                                        roomId = contractData.roomId,
                                        onResponse = {
                                            Toast.makeText(context,"อนุมัติสัญญาสำเร็จ",Toast.LENGTH_SHORT).show()
                                            navController.popBackStack()
                                        },
                                        onElse = {
                                            Toast.makeText(context,"เปลี่ยนสถานะห้องไม่สำเร็จ",Toast.LENGTH_SHORT).show()
                                        },
                                        onFailure = {t->
                                            Toast.makeText(context,"Error Check LogCat",Toast.LENGTH_SHORT).show()
                                            t.message?.let { Log.e("Error", t.message!!) }
                                        }
                                    )
                                },
                                onElse = {
                                    Toast.makeText(context,"อนุมัติสัญญาล้มเหลว",Toast.LENGTH_SHORT).show()
                                },
                                onFailure = { t->
                                    Toast.makeText(context,"Error Check LogCat",Toast.LENGTH_SHORT).show()
                                    t.message?.let { Log.e("Error", t.message!!) }
                                }
                            )
                        }
                    }
                ) {
                    Text("ตกลง")
                }
            },
            dismissButton = {
                TextButton(onClick = {approveAlert = false}) {
                    Text("ยกเลิก")
                }
            },
            containerColor = Color.White,
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(10.dp)
            )
        )
    }
}