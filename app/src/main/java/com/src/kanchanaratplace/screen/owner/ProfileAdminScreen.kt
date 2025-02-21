package com.src.kanchanaratplace.screen.owner

import android.inputmethodservice.Keyboard.Row
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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

data class ProfileAdminMenu(
    val icon : Int,
    val title : String,
    val navigateTo : String?
)

@Composable
fun ProfileAdminScaffold(navController: NavHostController){
    BaseScaffold(navController) {
        ProfileAdminScreen(navController)
    }
}

@Composable
fun ProfileAdminScreen(navController : NavHostController){
    val profileAdminMenuList = listOf<ProfileAdminMenu>(
        ProfileAdminMenu(R.drawable.profile_admin_apartment,"ข้อมูลหอพัก",null),
        ProfileAdminMenu(R.drawable.profile_admin_reservation,"ระบบการจอง",null),
        ProfileAdminMenu(R.drawable.profile_admin_service_charge,"ค่าบริการ",null),
        ProfileAdminMenu(R.drawable.profile_admin_room,"ห้องพัก",null),
        ProfileAdminMenu(R.drawable.profile_admin_more_detail,"รายละเอียดเพิ่มเติม",null)
    )

    val scrollState = rememberScrollState()

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color(240, 240, 240, 255))
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        Row (
            modifier = Modifier.fillMaxWidth()
                .background(Color(94, 144, 255, 255))
                .padding(vertical = 20.dp, horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(R.drawable.example_pic1_1),
                contentScale = ContentScale.Fit,
                contentDescription = null,
                modifier = Modifier.size(67.dp)
            )

            Column (
                horizontalAlignment = Alignment.Start
            ){
                Text(
                    text = "กาญจนรัตน์ เพลส",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ){
                    Text(
                        text = "ประกันระบบหมดอายุ: 4 มีนาคม 2570",
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.background(Color.White)
                            .clip(RoundedCornerShape(10.dp))
                            .padding(5.dp)
                            .border(
                                width = 1.dp,
                                color = Color.Transparent
                            )
                    )

                    Button(
                        onClick = {

                        },
                        shape = RoundedCornerShape(10.dp),
                        border = BorderStroke(
                            width = 2.dp,
                            color = Color.White
                        ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            text = "ต่อประกัน",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }

        Column (
            modifier = Modifier.fillMaxWidth()
                .background(Color.White)
                .padding(bottom = 10.dp),
        ){
            Column (
                modifier = Modifier.fillMaxWidth()
                    .padding(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ){
                Text(
                    text = "การจัดการ",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.align(Alignment.Start)
                )

                profileAdminMenuList.forEachIndexed { index,profile->
                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .padding(horizontal = 15.dp)
                            .clickable {
                                if (profile.navigateTo != null){
                                    navController.navigate(profile.navigateTo)
                                }
                            }.padding(5.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Image(
                                painter = painterResource(profile.icon),
                                contentDescription = null,
                                contentScale = ContentScale.Fit,
                                modifier = Modifier.size(32.dp)
                            )

                            Text(
                                text = profile.title,
                                fontSize = 18.sp
                            )
                        }

                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = null,
                            tint = Color(179, 179, 179, 255)
                        )
                    }

                    if(index != profileAdminMenuList.size - 1){
                        HorizontalDivider(color = Color.LightGray)
                    }
                }
            }
        }

        Column (
            modifier = Modifier.fillMaxWidth()
                .background(Color.White),
        ){
            Column(
                modifier = Modifier.fillMaxWidth()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "อื่นๆ",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.align(Alignment.Start)
                )

                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(15.dp),
                    verticalAlignment = Alignment.Top
                ){
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ){
                        Button(
                            onClick = {
                                navController.navigate(Screen.MemberDataList.route)
                            },
                            shape = CircleShape,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(94, 144, 255, 255),
                                contentColor = Color.White
                            ),
                            modifier = Modifier.size(60.dp),
                            contentPadding = PaddingValues(5.dp)
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.profile_admin_other_data),
                                contentDescription = null,
                                modifier = Modifier.size(33.dp)
                            )
                        }

                        Text(
                            text = "ข้อมูลผู้ใช้",
                            fontSize = 14.sp,
                            modifier = Modifier.width(100.dp),
                            textAlign = TextAlign.Center

                        )
                    }

                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ){
                        Button(
                            onClick = {

                            },
                            shape = CircleShape,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(94, 144, 255, 255),
                                contentColor = Color.White
                            ),
                            modifier = Modifier.size(60.dp),
                            contentPadding = PaddingValues(5.dp)
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.profile_admin_other_summarize),
                                contentDescription = null,
                                modifier = Modifier.size(33.dp)
                            )
                        }

                        Text(
                            text = "สรุปรายรับ - รายจ่าย",
                            fontSize = 14.sp,
                            modifier = Modifier.width(95.dp),
                            textAlign = TextAlign.Center
                        )
                    }

                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ){
                        Button(
                            onClick = {

                            },
                            shape = CircleShape,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(94, 144, 255, 255),
                                contentColor = Color.White
                            ),
                            modifier = Modifier.size(60.dp),
                            contentPadding = PaddingValues(5.dp)
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.profile_admin_other_dashboard),
                                contentDescription = null,
                                modifier = Modifier.size(33.dp)
                            )
                        }

                        Text(
                            text = "แดชบอร์ด",
                            fontSize = 14.sp,
                            modifier = Modifier.width(90.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}