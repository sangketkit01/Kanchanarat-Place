package com.src.kanchanaratplace.screen.member

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
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
import com.src.kanchanaratplace.api_util.getNewContractsUtility
import com.src.kanchanaratplace.api_util.getOneRoomUtility
import com.src.kanchanaratplace.component.BaseScaffold
import com.src.kanchanaratplace.data.Bill
import com.src.kanchanaratplace.data.DefaultRooms
import com.src.kanchanaratplace.navigation.Screen
import com.src.kanchanaratplace.session.MemberSharePreferencesManager

@Composable
fun MemberApartmentScaffold(navController: NavHostController){
    BaseScaffold(navController) {
        MemberApartmentScreen(navController)
    }
}

data class MemberApartmentMenu(
    val icon : Painter,
    val title : String,
    val navigateTo : String
)

@Composable
fun MemberApartmentScreen(navController : NavHostController){
    val context = LocalContext.current
    val sharePreferences = remember { MemberSharePreferencesManager(context) }

    var roomData by remember { mutableStateOf<DefaultRooms?>(null) }
    var billData by remember { mutableStateOf<Bill?>(null) }

    val firstRowMenu = listOf(
        MemberApartmentMenu(painterResource(R.drawable.bill_menu),"บิลค่าเช่า",Screen.MemberBill.route),
        MemberApartmentMenu(painterResource(R.drawable.repair_menu),"แจ้งซ่อม",Screen.First.route),
        MemberApartmentMenu(painterResource(R.drawable.package_menu),"พัสดุ",Screen.First.route),
        MemberApartmentMenu(painterResource(R.drawable.news_menu),"ข่าวสาร",Screen.First.route)
    )

    val secondRowMenu = listOf(
        MemberApartmentMenu(painterResource(R.drawable.leave_menu),"แจ้งย้ายออก",Screen.First.route),
        MemberApartmentMenu(painterResource(R.drawable.truck_menu),"ขนของ",Screen.First.route),
        MemberApartmentMenu(painterResource(R.drawable.contract_menu),"สัญญา",Screen.First.route),
        MemberApartmentMenu(painterResource(R.drawable.info_menu),"คู่มือ",Screen.First.route)
    )

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
                    .padding(top = 10.dp)
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

            Card (
                modifier = Modifier.fillMaxWidth()
                    .padding(vertical = 10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(
                    width = 1.dp,
                    color = Color.LightGray,
                )
            ){
                Row (
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 15.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    firstRowMenu.forEach { menu->
                        Column (
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Button(
                                onClick = {
                                    navController.navigate(menu.navigateTo)
                                },
                                shape = RoundedCornerShape(10.dp),
                                elevation = ButtonDefaults.buttonElevation(
                                    defaultElevation = 4.dp
                                ),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(172, 198, 255, 255)
                                ),
                                modifier = Modifier.width(67.dp).height(65.dp)
                            ) {
                                Icon(
                                    painter = menu.icon,
                                    contentDescription = null,
                                    modifier = Modifier.size(50.dp),
                                    tint = Color.Black
                                )
                            }

                            Text(
                                text = menu.title,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(vertical = 5.dp)
                            )
                        }
                    }
                }

                Row (
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 15.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    secondRowMenu.forEach { menu->
                        Column (
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Button(
                                onClick = {

                                },
                                shape = RoundedCornerShape(10.dp),
                                elevation = ButtonDefaults.buttonElevation(
                                    defaultElevation = 4.dp
                                ),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(172, 198, 255, 255)
                                ),
                                modifier = Modifier.width(67.dp).height(65.dp)
                            ) {
                                Icon(
                                    painter = menu.icon,
                                    contentDescription = null,
                                    modifier = Modifier.size(50.dp),
                                    tint = Color.Black
                                )
                            }

                            Text(
                                text = menu.title,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(vertical = 5.dp)
                            )
                        }
                    }
                }
            }

            Column (
                modifier = Modifier.fillMaxWidth()
                    .padding(vertical = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = "การชำระเงิน",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.align(Alignment.Start)
                        .padding(start = 10.dp, bottom = 5.dp)
                )

                Card (
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(
                        width = 1.dp,
                        color = Color.LightGray
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 4.dp
                    )
                ){
                    Row (
                        modifier = Modifier.fillMaxWidth()
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Column (
                            horizontalAlignment = Alignment.Start
                        ){
                            Row (
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Text(
                                    text = "ค้างชำระ",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.SemiBold
                                )

                                Spacer(modifier = Modifier.width(8.dp))

                                Image(
                                    painter = painterResource(R.drawable.red_warning),
                                    contentScale = ContentScale.Fit,
                                    contentDescription = null,
                                    modifier = Modifier.size(25.dp)
                                )
                            }

                            Text(
                                text = "บิลค่าเช่าประจำเดือน มกราคม /2025\n" +
                                        "จำนวนเงิน 4,850 บาท",
                                fontSize = 13.sp
                            )

                        }

                        Image(
                            painter = painterResource(R.drawable.red_paper),
                            contentDescription = null,
                            modifier = Modifier.size(50.dp),
                            contentScale = ContentScale.Fit
                        )
                    }
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth()
                    .padding(vertical = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "สถานะการแจ้งซ่อม",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.align(Alignment.Start)
                        .padding(start = 10.dp, bottom = 5.dp)
                )

                Card (
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(
                        width = 1.dp,
                        color = Color.LightGray
                    )
                ){
                    Column (
                        modifier = Modifier.fillMaxSize()
                            .padding(10.dp)
                            .height(115.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Text(
                            text = "ยังไม่มีสถานการแจ้งซ่อม",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(165, 165, 165, 255)
                        )
                    }
                }
            }
        }
    }
}