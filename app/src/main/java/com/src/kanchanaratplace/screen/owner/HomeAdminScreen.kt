package com.src.kanchanaratplace.screen.owner

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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

    val menuList = listOf<AdminHomeMenu>(
        AdminHomeMenu(R.drawable.admin_bed,"ห้องพักทั้งหมด",Screen.HomeAdmin.route),
        AdminHomeMenu(R.drawable.admin_bed,"จัดการห้องพัก",Screen.HomeAdmin.route),
        AdminHomeMenu(R.drawable.admin_booking,"แจ้งซ่อม",Screen.HomeAdmin.route),
        AdminHomeMenu(R.drawable.admin_person,"สัญญาหอ",Screen.HomeAdmin.route),
        AdminHomeMenu(R.drawable.admin_money,"สรุปยอด",Screen.HomeAdmin.route),
        AdminHomeMenu(R.drawable.admin_money,"แดชบอร์ด",Screen.HomeAdmin.route),
        AdminHomeMenu(R.drawable.admin_money,"สถานะชำระเงิน",Screen.HomeAdmin.route)
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
                    .padding(vertical = 25.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Image(
                    painter = painterResource(R.drawable.example_pic1),
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    modifier = Modifier.width(330.dp).height(201.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column (
            modifier = Modifier.fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "กิจกรรม",
                fontWeight = FontWeight.SemiBold,
                fontSize = 26.sp,
                modifier = Modifier.padding(start = 5.dp, bottom = 8.dp)
                    .align(Alignment.Start)
            )

            Card (
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(
                    width = 1.dp,
                    color = Color.LightGray
                ),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ){
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier.fillMaxWidth()
                        .height(500.dp),
                    contentPadding = PaddingValues(20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    items(menuList) {menu->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Card (
                                shape = RoundedCornerShape(10.dp),
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = 4.dp
                                ),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color(172, 198, 255, 255)
                                ),
                                modifier = Modifier.size(80.dp)
                                    .clickable {

                                    }
                            ){
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Image(
                                        painter = painterResource(menu.icon),
                                        contentDescription = null,
                                        contentScale = ContentScale.Fit,
                                        modifier = Modifier.size(40.dp)
                                    )
                                }
                            }

                            Text(
                                text = menu.title,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.width(80.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}