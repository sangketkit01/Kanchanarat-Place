package com.src.kanchanaratplace.screen.owner

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.src.kanchanaratplace.R
import com.src.kanchanaratplace.api_util.approveRepairUtility
import com.src.kanchanaratplace.api_util.getOneRoomUtility
import com.src.kanchanaratplace.api_util.getRepairImages
import com.src.kanchanaratplace.api_util.getReservationUtility
import com.src.kanchanaratplace.api_util.getUnapprovedRepairUtility
import com.src.kanchanaratplace.component.SampleScaffold
import com.src.kanchanaratplace.data.DefaultRooms
import com.src.kanchanaratplace.data.Repair
import com.src.kanchanaratplace.data.RepairImages
import com.src.kanchanaratplace.navigation.Screen

@Composable
fun RepairListScaffold(navController: NavHostController){
    SampleScaffold(navController,"แจ้งซ่อม"){
        RepairListScreen(navController)
    }
}

@Composable
fun RepairListScreen(navController : NavHostController){
    var acceptAlert by remember { mutableStateOf(false) }

    var repairList by remember { mutableStateOf<List<Repair>>(emptyList()) }

    var selectedRepair by remember { mutableStateOf("") }
    var selectedRoom by remember { mutableStateOf("") }
    var selectedRepairId by remember { mutableIntStateOf(0) }

    val context = LocalContext.current
    val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current
    val lifecycleState by lifecycleOwner.lifecycle.currentStateFlow.collectAsState()
    LaunchedEffect (lifecycleState){
        when(lifecycleState){
            Lifecycle.State.DESTROYED -> {}
            Lifecycle.State.INITIALIZED -> {}
            Lifecycle.State.CREATED -> {}
            Lifecycle.State.STARTED -> {}
            Lifecycle.State.RESUMED -> {
                getUnapprovedRepairUtility(
                    onResponse = { response->
                        repairList = response
                    },
                    onElse = { elseResponse ->

                    },
                    onFailure = {

                    }
                )
            }
        }
    }

    val scrollState = rememberScrollState()
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        Column (
            modifier = Modifier.fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ){
            repairList.forEach { repair->
                var imageList by remember { mutableStateOf<List<RepairImages>>(emptyList()) }
                var room by remember { mutableStateOf<DefaultRooms?>(null) }

                getRepairImages(
                    repair.repairId,
                    onResponse = { response->
                        imageList = response
                    },
                    onElse = { elseResponse->
                        Toast.makeText(context,"Cannot fetch repair images",Toast.LENGTH_SHORT)
                            .show()
                        Log.e("Error",elseResponse.message())
                    },
                    onFailure = { t->
                        Toast.makeText(context,"Error onFailure",Toast.LENGTH_SHORT)
                            .show()
                        Log.e("Error",t.message ?: "No message")
                    }
                )

                getOneRoomUtility(
                    repair.roomId,
                    onResponse = { response->
                        room = response
                    },
                    onElse = { elseResponse->
                        Toast.makeText(context,"Cannot fetch room data",Toast.LENGTH_SHORT)
                            .show()
                        Log.e("Error",elseResponse.message())
                    },
                    onFailure = { t->
                        Toast.makeText(context,"Error onFailure",Toast.LENGTH_SHORT)
                            .show()
                        Log.e("Error",t.message ?: "No message")
                    }
                )

                Card (
                    modifier = Modifier.fillMaxWidth()
                        .clickable {
                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                "repair_data",repair
                            )

                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                "images",imageList
                            )
                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                "room_data",room
                            )
                            navController.navigate(Screen.RepairDetail.route)
                        },
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ){
                    Row (
                        modifier = Modifier.fillMaxWidth()
                            .padding(10.dp),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Column(
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = "${repair.createdAt}",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(165,165,165)
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                "ห้อง ${room?.roomCode}",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(94,144,255)
                            )

                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = repair.repairTitle,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )

                            if(repair.repairDetail.isNotEmpty()){
                                Text(
                                    text = repair.repairDetail,
                                    fontSize = 12.sp
                                )
                            }else{
                                Text(
                                    text = "ไม่มีรายละเอียดเพิ่มเติม",
                                    fontSize = 12.sp
                                )
                            }
                        }

                        Column (
                            horizontalAlignment = Alignment.End,
                        ){
                            if (imageList.isNotEmpty()){
                                Box(
                                    modifier = Modifier.width(100.dp)
                                        .height(120.dp)
                                ){
                                    Image(
                                        painter = rememberAsyncImagePainter(
                                            model = imageList[0].imagePath.replace(
                                                "../uploads/","http://10.0.2.2:3000/uploads/"
                                            )
                                        ),
                                        contentScale = ContentScale.Crop,
                                        contentDescription = null,
                                        modifier = Modifier.fillMaxSize()
                                    )

                                    if(imageList.size - 1 > 0){
                                        Text(
                                            text = "+${imageList.size - 1}",
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White,
                                            modifier = Modifier.align(Alignment.BottomEnd)
                                        )
                                    }
                                }
                            }

                            TextButton(
                                onClick = {
                                    selectedRoom = room?.roomCode ?: ""
                                    selectedRepair = repair.repairTitle
                                    selectedRepairId  =repair.repairId
                                    acceptAlert = true
                                },
                                shape = CircleShape,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(94,144,255),
                                    contentColor = Color.White
                                ),
                                modifier = Modifier.width(100.dp)
                            ){
                                Text(
                                    text = "รับเรื่อง",
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

    if(acceptAlert){
        AlertDialog(
            onDismissRequest = {
                acceptAlert = false
            },
            title = {
                Text("ยืนยันรับการซ่อม")
            },
            text = {
                Text("ท่านต้องการยืนยันรับการซ่อมเรื่อง '$selectedRepair' ห้อง '$selectedRoom' " +
                        "หรือไม่")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        approveRepairUtility(
                            selectedRepairId,
                            onResponse = {
                                Toast.makeText(context,"Approve repair successfully",Toast.LENGTH_SHORT)
                                    .show()
                                navController.navigate(Screen.RepairPending.route)
                                acceptAlert = false
                            },
                            onElse = { elseResponse->
                                Toast.makeText(context,"Approve repair failed",Toast.LENGTH_SHORT)
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
                        acceptAlert = false
                    }
                ){
                    Text("ยกเลิก")
                }
            }
        )
    }
}