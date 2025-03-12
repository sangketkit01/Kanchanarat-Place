package com.src.kanchanaratplace.screen.owner

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import com.src.kanchanaratplace.R
import com.src.kanchanaratplace.api_util.approveLeavingUtility
import com.src.kanchanaratplace.api_util.getAllReservedUtility
import com.src.kanchanaratplace.api_util.getMemberByRoomUtility
import com.src.kanchanaratplace.api_util.getOneRoomUtility
import com.src.kanchanaratplace.api_util.getUnapprovedLeavingUtility
import com.src.kanchanaratplace.api_util.rejectLeavingUtility
import com.src.kanchanaratplace.component.ButtonWithBadge
import com.src.kanchanaratplace.component.SampleScaffold
import com.src.kanchanaratplace.data.DefaultRooms
import com.src.kanchanaratplace.data.Leaving
import com.src.kanchanaratplace.data.Member
import com.src.kanchanaratplace.data.Reservation
import com.src.kanchanaratplace.navigation.Screen
import com.src.kanchanaratplace.status.OtherStatus
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

@Composable
fun LeavingListScaffold(navController: NavHostController){
    SampleScaffold(navController,"ขอร้องย้ายออก"){
        LeavingListScreen(navController)
    }
}

@Composable
fun LeavingListScreen(navController : NavHostController){

    val scrollState = rememberScrollState()

    val context = LocalContext.current

    val leavingList = remember { mutableStateListOf<Leaving>() }
    val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current
    val lifecycleState by lifecycleOwner.lifecycle.currentStateFlow.collectAsState()

    var rejectAlert by remember { mutableStateOf(false) }
    var rejectReason by remember { mutableStateOf("") }
    var confirmReject by remember { mutableStateOf(false) }

    var approveAlert by remember { mutableStateOf(false) }

    var selectedRoom by remember { mutableStateOf<DefaultRooms?>(null) }
    var selectedLeaving by remember { mutableStateOf<Leaving?>(null) }

    LaunchedEffect (lifecycleState){
        when(lifecycleState){
            Lifecycle.State.DESTROYED -> {}
            Lifecycle.State.INITIALIZED -> {}
            Lifecycle.State.CREATED -> {}
            Lifecycle.State.STARTED -> {}
            Lifecycle.State.RESUMED -> {
                getUnapprovedLeavingUtility(
                    onResponse = {response->
                        leavingList.addAll(response)
                    },
                    onElse = {
                        Toast.makeText(context,"Data not found",Toast.LENGTH_SHORT).show()
                    },
                    onFailure = {t->
                        Toast.makeText(context,"Error Check LogCat",Toast.LENGTH_SHORT).show()
                        t.message?.let { Log.e("Error",it) }
                    }
                )
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
            .verticalScroll(scrollState)
            .background(
                color = Color.White
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "อนุมัติคำร้อง",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 10.dp , horizontal = 20.dp)
                .align(Alignment.Start)
        )

        leavingList.forEach { data->
            var room by remember { mutableStateOf<DefaultRooms?>(null) }
            var member by remember { mutableStateOf<Member?>(null) }

            val isoFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
            isoFormatter.timeZone = TimeZone.getTimeZone("UTC")

            val thaiFormatter = SimpleDateFormat("d MMMM yyyy", Locale("th", "TH"))

            val reportDate = isoFormatter.parse(data.reportDate)
            val moveOutDate = isoFormatter.parse(data.moveOutDate)

            val reportDateThai = reportDate?.let { thaiFormatter.format(it) }
            val moveOutDateThai = moveOutDate?.let { thaiFormatter.format(it) }


            getOneRoomUtility(
                data.roomId,
                onResponse = {response->
                    room = response
                },
                onElse = {
                    Toast.makeText(context,"Data not found", Toast.LENGTH_SHORT).show()
                },
                onFailure = {t->
                    Toast.makeText(context,"Error Check LogCat", Toast.LENGTH_SHORT).show()
                    t.message?.let { Log.e("Error",it) }
                }
            )

            getMemberByRoomUtility(
                data.roomId,
                onResponse = {response->
                    member = response
                },
                onElse = {
                    Toast.makeText(context,"Data not found", Toast.LENGTH_SHORT).show()
                },
                onFailure = {t->
                    Toast.makeText(context,"Error Check LogCat", Toast.LENGTH_SHORT).show()
                    t.message?.let { Log.e("Error",it) }
                }
            )

            Card (
                modifier = Modifier.fillMaxWidth()
                    .padding(10.dp),
                border = BorderStroke(
                    width = 2.dp,
                    color = Color.LightGray,
                ),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp)
            ){
                Row (
                    modifier = Modifier.fillMaxWidth()
                        .padding(20.dp),
                    verticalAlignment = if(data.statusId == OtherStatus.PENDING.id) Alignment.Top
                                        else Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ){
                    Column {
                        Row (
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(15.dp)
                        ){
                            Image(
                                painter = painterResource(R.drawable.purple_house),
                                contentDescription = null,
                                modifier = Modifier.size(60.dp)
                            )

                            Column {
                                Text(
                                    text = "ชั้น ${room?.roomFloor} ห้อง ${room?.roomCode}",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.SemiBold
                                )

                                Spacer(modifier = Modifier.height(5.dp))

                                Text(
                                    text = "คุณ ${member?.name?.split(" ")?.get(0) ?: "Unknown"}",
                                    fontSize = 16.sp
                                )
                            }
                        }
                        Text(
                            text = "วันที่แจ้ง: $reportDateThai",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFA5A5A5)
                        )

                        Text(
                            text = "วันที่ย้ายออก: $moveOutDateThai",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFA5A5A5)
                        )
                    }

                    Spacer(modifier = Modifier.width(25.dp))

                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ){
                        if(data.statusId == OtherStatus.PENDING.id){
                            TextButton(
                                onClick = {
                                    selectedRoom = room
                                    selectedLeaving = data
                                    approveAlert = true
                                },
                                shape = RoundedCornerShape(10.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF14AE5C),
                                    contentColor = Color.White
                                ),
                                modifier = Modifier.width(110.dp)
                            ){
                                Text(
                                    text = "อนุมัติ",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            TextButton(
                                onClick = {
                                    selectedRoom = room
                                    selectedLeaving = data
                                    rejectAlert = true
                                },
                                shape = RoundedCornerShape(10.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFFB2323),
                                    contentColor = Color.White
                                ),
                                modifier = Modifier.width(110.dp)
                            ){
                                Text(
                                    text = "ปฏิเสธ",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }else if(data.statusId == OtherStatus.SUCCESS.id){
                            TextButton(
                                onClick = {

                                },
                                shape = RoundedCornerShape(10.dp),
                                colors = ButtonDefaults.buttonColors(
                                    contentColor = Color.White
                                ),
                                enabled = false
                            ){
                                Text(
                                    text = "อนุมัติคำร้อง",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        else if(data.statusId == OtherStatus.REJECTED.id){
                            TextButton(
                                onClick = {

                                },
                                shape = RoundedCornerShape(10.dp),
                                colors = ButtonDefaults.buttonColors(
                                    contentColor = Color.White
                                ),
                                enabled = false
                            ){
                                Text(
                                    text = "ปฏิเสธคำร้อง",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    if(rejectAlert){
        AlertDialog(
            onDismissRequest = {
                rejectAlert = false
            },
            title = { Text("เหตุผล") },
            text = {
                Column(modifier = Modifier.fillMaxWidth()){
                    OutlinedTextField(
                        value = rejectReason,
                        onValueChange = {newValue->
                            rejectReason = newValue
                        },
                        shape = RoundedCornerShape(10.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedIndicatorColor = Color.LightGray,
                            unfocusedIndicatorColor = Color.LightGray
                        ),
                        modifier = Modifier.fillMaxWidth()
                            .height(200.dp).align(Alignment.CenterHorizontally)
                    )

                    Row (
                        modifier = Modifier.align(Alignment.End)
                            .padding(top = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ){
                        TextButton(
                            onClick = {rejectAlert = false},
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFD9D9D9),
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(10.dp)
                        ){
                            Text(
                                text = "ยกเลิก",
                                fontWeight = FontWeight.Bold
                            )
                        }

                        TextButton(
                            onClick = {
                                rejectAlert = false
                                confirmReject = true
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF32A129),
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(10.dp),
                            enabled = rejectReason.isNotEmpty()
                        ){
                            Text(
                                text = "บันทึก",
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            },
            confirmButton = {

            },
            containerColor = Color.White
        )
    }

    if(confirmReject){
        AlertDialog(
            onDismissRequest = {confirmReject = false},
            title = {
                Text("ยืนยัน")
            },
            text = {
                Text("คุณต้องการการปฏิเสธปฏิเสธคำร้องขอย้ายออกของห้อง ${selectedRoom?.roomCode ?: ""} หรือไม่")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        rejectLeavingUtility(
                            selectedLeaving?.leavingId ?: 0,
                            rejectReason,
                            onResponse = {
                                Toast.makeText(context,"Reject leaving successfully",Toast.LENGTH_SHORT)
                                    .show()
                                navController.navigate(Screen.LeavingList.route) {
                                    popUpTo(Screen.LeavingList.route) { inclusive = true }
                                }
                            },
                            onElse = { elseResponse->
                                Toast.makeText(context,"Reject leaving failed",Toast.LENGTH_SHORT)
                                    .show()
                                Log.e("Error",elseResponse.message())
                            },
                            onFailure = { t->
                                Toast.makeText(context,"Error onFailure",Toast.LENGTH_SHORT)
                                    .show()
                                Log.e("Error",t.message ?: "No message")
                            }
                        )
                    }
                ){
                    Text("ตกลง")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        confirmReject = false
                    }
                ){
                    Text("ยกเลิก")
                }
            }
        )
    }

    if(approveAlert){
        AlertDialog(
            onDismissRequest = {approveAlert = false},
            title = {
                Text(
                    text = "ยืนยัน"
                )
            },
            text = {
                Text(
                    text = "คุณต้องการอนุมัติคำร้องขอย้ายออกของห้อง ${selectedRoom?.roomCode ?: ""} หรือไม่"
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        approveLeavingUtility(
                            selectedLeaving?.leavingId ?: 0,
                            onResponse = {
                                Toast.makeText(context,"Approve leaving successfully",Toast.LENGTH_SHORT)
                                    .show()
                                navController.navigate(Screen.LeavingList.route) {
                                    popUpTo(Screen.LeavingList.route) { inclusive = true }
                                }
                            },
                            onElse = { elseResponse->
                                Toast.makeText(context,"Approve leaving failed",Toast.LENGTH_SHORT)
                                    .show()
                                Log.e("Error",elseResponse.message())
                            },
                            onFailure = { t->
                                Toast.makeText(context,"Error onFailure",Toast.LENGTH_SHORT)
                                    .show()
                                Log.e("Error",t.message ?: "No message")
                            }
                        )
                    }
                ){
                    Text("ตกลง")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        approveAlert = false
                    }
                ){
                    Text("ยกเลิก")
                }
            }
        )
    }
}