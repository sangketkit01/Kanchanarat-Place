package com.src.kanchanaratplace.screen.owner

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.src.kanchanaratplace.R
import com.src.kanchanaratplace.component.BaseScaffold
import com.src.kanchanaratplace.navigation.Screen

data class AdminHomeMenu(
    val icon : Int,
    val title : String,
    val navigateTo : String
)

@Composable
fun HomeAdminScaffold(navController: NavHostController){
    BaseScaffold(navController) {
        HomeAdminScreen(navController)
    }
}

@Composable
fun HomeAdminScreen(navController : NavHostController){
    val scrollState = rememberScrollState()

    val repairList = listOf<AdminHomeMenu>(
        AdminHomeMenu(R.drawable.repair_admin , "แจ้งซ่อม",Screen.RepairList.route),
        AdminHomeMenu(R.drawable.pending_repair,"กำลังดำเนินการ",Screen.RepairPending.route),
        AdminHomeMenu(R.drawable.success_repair,"ซ่อมแล้ว",Screen.RepairSuccess.route)
    )

    val contractList = listOf<AdminHomeMenu>(
        AdminHomeMenu(R.drawable.approve_reservation , "อนุมัติการจอง",Screen.ReservedList.route),
        AdminHomeMenu(R.drawable.contract,"ทำสัญญา",Screen.ContractList.route),
        AdminHomeMenu(R.drawable.leaving_form,"คำขอย้าย",Screen.LeavingList.route)
    )

    val otherFirstLine = listOf<AdminHomeMenu>(
        AdminHomeMenu(R.drawable.check_payment,"การชำระเงิน",Screen.CheckMemberPayment.route),
        AdminHomeMenu(R.drawable.revenue,"สรุปยอด",""),
        AdminHomeMenu(R.drawable.dashboard,"แดชบอร์ด",""),
    )

    val otherSecondLine = listOf<AdminHomeMenu>(
        AdminHomeMenu(R.drawable.member_data,"ข้อมูลผู้เช่า",""),
        AdminHomeMenu(R.drawable.all_room,"ห้องพัก",""),
        AdminHomeMenu(R.drawable.other_detail,"เพิ่มเติม","")
    )

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "กาญจนรัตน์ เพลส",
            fontSize = 26.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.align(Alignment.Start)
                .padding(top = 10.dp , start = 25.dp)
        )

        Card (
            modifier = Modifier.fillMaxWidth()
                .padding(20.dp),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(172, 198, 255, 255)
            )
        ){
            Column (
                modifier = Modifier.fillMaxWidth()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Image(
                    painter = painterResource(R.drawable.example_pic10),
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    modifier = Modifier.width(361.dp).height(111.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column (
            modifier = Modifier.fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Text(
                text = "แจ้งซ่อม",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 5.dp , bottom = 10.dp)
                    .align(Alignment.Start)
            )

            Card (
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ){
                Row (
                    modifier = Modifier.fillMaxWidth()
                        .padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ){
                    repairList.forEach { repair->
                        Card (
                            modifier = Modifier.size(100.dp)
                                .clickable {
                                    navController.navigate(repair.navigateTo)
                                },
                            shape = RoundedCornerShape(10.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(215,227,255)
                            )
                        ){
                            Column (
                                modifier = Modifier.fillMaxWidth()
                                    .padding(10.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ){
                                Image(
                                    painter = painterResource(repair.icon),
                                    contentDescription = null,
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier.size(60.dp)
                                )

                                Text(
                                    text = repair.title,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }
                }
            }
        }

        Column (
            modifier = Modifier.fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Text(
                text = "เกี่ยวกับสัญญา",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 5.dp , bottom = 10.dp)
                    .align(Alignment.Start)
            )

            Card (
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ){
                Row (
                    modifier = Modifier.fillMaxWidth()
                        .padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ){
                    contractList.forEach { contract->
                        Card (
                            modifier = Modifier.size(100.dp)
                                .clickable {
                                    navController.navigate(contract.navigateTo)
                                },
                            shape = RoundedCornerShape(10.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(215,227,255)
                            )
                        ){
                            Column (
                                modifier = Modifier.fillMaxWidth()
                                    .padding(10.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ){
                                Image(
                                    painter = painterResource(contract.icon),
                                    contentDescription = null,
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier.size(60.dp)
                                )

                                Text(
                                    text = contract.title,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }
                }
            }
        }

        Column (
            modifier = Modifier.fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Text(
                text = "กิจกรรมอื่นๆ",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 5.dp , bottom = 10.dp)
                    .align(Alignment.Start)
            )

            Card (
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ){
                Row (
                    modifier = Modifier.fillMaxWidth()
                        .padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ){
                    otherFirstLine.forEach { first->
                        Card (
                            modifier = Modifier.size(100.dp)
                                .clickable {
                                    navController.navigate(first.navigateTo)
                                },
                            shape = RoundedCornerShape(10.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(215,227,255)
                            )
                        ){
                            Column (
                                modifier = Modifier.fillMaxWidth()
                                    .padding(10.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ){
                                Image(
                                    painter = painterResource(first.icon),
                                    contentDescription = null,
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier.size(60.dp)
                                )

                                Text(
                                    text = first.title,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }
                }

                Row (
                    modifier = Modifier.fillMaxWidth()
                        .padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ){
                    otherSecondLine.forEach { second->
                        Card (
                            modifier = Modifier.size(100.dp)
                                .clickable {

                                },
                            shape = RoundedCornerShape(10.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(215,227,255)
                            )
                        ){
                            Column (
                                modifier = Modifier.fillMaxWidth()
                                    .padding(10.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ){
                                Image(
                                    painter = painterResource(second.icon),
                                    contentDescription = null,
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier.size(60.dp)
                                )

                                Text(
                                    text = second.title,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}