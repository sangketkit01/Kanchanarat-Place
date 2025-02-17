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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import com.src.kanchanaratplace.api_util.getAllReservedUtility
import com.src.kanchanaratplace.api_util.getNewContractsUtility
import com.src.kanchanaratplace.api_util.getOneRoomUtility
import com.src.kanchanaratplace.api_util.getReservationUtility
import com.src.kanchanaratplace.component.ButtonWithBadge
import com.src.kanchanaratplace.component.SampleScaffold
import com.src.kanchanaratplace.data.Contract
import com.src.kanchanaratplace.data.DefaultRooms
import com.src.kanchanaratplace.data.Reservation
import com.src.kanchanaratplace.navigation.Screen

@Composable
fun ContractListScaffold(navController: NavHostController){
    SampleScaffold(navController,"สัญญา") {
        ContractListScreen(navController)
    }
}

@Composable
fun ContractListScreen(navController : NavHostController){
    val scrollState = rememberScrollState()

    val context = LocalContext.current

    val contractList = remember { mutableStateListOf<Contract>() }
    val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current
    val lifecycleState by lifecycleOwner.lifecycle.currentStateFlow.collectAsState()
    LaunchedEffect (lifecycleState){
        when(lifecycleState){
            Lifecycle.State.DESTROYED -> {}
            Lifecycle.State.INITIALIZED -> {}
            Lifecycle.State.CREATED -> {}
            Lifecycle.State.STARTED -> {}
            Lifecycle.State.RESUMED -> {
                getNewContractsUtility(
                    onResponse = {response->
                        contractList.addAll(response)
                    },
                    onElse = {
                        Toast.makeText(context,"Data not found", Toast.LENGTH_SHORT).show()
                    },
                    onFailure = {t->
                        Toast.makeText(context,"Error Check LogCat", Toast.LENGTH_SHORT).show()
                        t.message?.let { Log.e("Error",it) }
                    }
                )
            }
        }
    }

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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(240, 240, 240))
        ) {
            ButtonWithBadge(
                text = "อนุมัติการจอง",
                badgeCount = 1,
                modifier = Modifier.weight(1f),
                background = Color(240, 240, 240, 255),
                onClick = {
                    navController.navigate(Screen.ReservedList.route)
                }
            )
            ButtonWithBadge(
                text = "ทำสัญญา",
                badgeCount = contractList.size,
                modifier = Modifier.weight(1f),
                background = Color.White,
                onClick = {
                    navController.navigate(Screen.ContractList.route)
                }
            )
        }

        contractList.forEach { contract->
            var room by remember { mutableStateOf<DefaultRooms?>(null) }
            var reservation by remember { mutableStateOf<Reservation?>(null) }

            getOneRoomUtility(
                contract.roomId,
                onResponse = { response->
                    room = response
                },
                onElse = {
                    Toast.makeText(context,"Data not Found",Toast.LENGTH_SHORT).show()
                },
                onFailure = { t->
                    Toast.makeText(context,"Error Check LogCat",Toast.LENGTH_SHORT).show()
                    t.message?.let { Log.e("Error",it) }
                }
            )

            getReservationUtility(
                contract.reservationId,
                onResponse = { response->
                    reservation = response
                },
                onElse = {
                    Toast.makeText(context,"Data not Found",Toast.LENGTH_SHORT).show()
                },
                onFailure = {t->
                    Toast.makeText(context,"Error Check LogCat",Toast.LENGTH_SHORT).show()
                    t.message?.let { Log.e("Error",it) }
                }
            )

            Card (
                modifier = Modifier
                    .fillMaxWidth()
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    Image(
                        painter = painterResource(R.drawable.purple_house),
                        contentDescription = null,
                        modifier = Modifier.size(60.dp)
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Column {
                        Text(
                            text = "ชั้น ${room?.roomFloor} ห้อง ${room?.roomCode}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        Spacer(modifier = Modifier.height(5.dp))

                        Text(
                            text = "คุณ ${reservation?.name?.split(" ")?.get(0)}",
                            fontSize = 16.sp
                        )

                        Text(
                            text = "${contract.createdAt}",
                            fontSize = 12.sp,
                            color = Color(165, 165, 165, 255)
                        )
                    }

                    Spacer(modifier = Modifier.width(30.dp))

                    Button(
                        onClick = {
                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                "contract_data" , contract
                            )
                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                "room_data",room
                            )
                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                "reservation_data",reservation
                            )

                            navController.navigate(Screen.ContractDetail.route)
                        },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(94, 144, 255, 255),
                            contentColor = Color.White
                        ),
                    ) {
                        Column (
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ){
                            Icon(
                                painter = painterResource(R.drawable.note),
                                contentDescription = null,
                                modifier = Modifier.size(35.dp)
                            )

                            Text(
                                text = "รายละเอียด",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }

    }
}