package com.src.kanchanaratplace

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.src.kanchanaratplace.navigation.Screen

@Composable
fun FirstScreen(navController : NavHostController) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier.fillMaxSize()
            .verticalScroll(scrollState).padding(vertical = 80.dp)
            .background(
                color = Color.White
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(Modifier.height(20.dp))
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.Start
        )
        {
            Text("ห้องพัก", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text("ห้องพัก", fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
        }

        Column (
            modifier = Modifier.fillMaxWidth().padding(10.dp)
                .background(Color(172, 198, 255, 255))
                .clip(RoundedCornerShape(8.dp)),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Column (
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Image(
                    painter = painterResource(id = R.drawable.example_pic1),
                    contentDescription = null,
                    modifier = Modifier.width(350.dp).height(215.dp)
                )
            }

//            Spacer(modifier = Modifier.height(10.dp))

            LazyRow(
                modifier = Modifier.padding(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(horizontal = 10.dp)
            ) {
                val images = listOf(
                    R.drawable.example_pic2,
                    R.drawable.example_pic3,
                    R.drawable.example_pic2,
                    R.drawable.example_pic2
                )

                items(images) { imageRes ->
                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = null,
                        modifier = Modifier
                            .width(129.dp)
                            .height(148.dp)
                            .clip(RoundedCornerShape(5.dp))
                    )
                }
            }
        }

        Row (
            modifier = Modifier.fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.End
        ){
            FilledTonalButton(
                onClick = {
                    navController.navigate(Screen.ApartmentDetail.route)
                },
                modifier = Modifier.clip(RoundedCornerShape(20.dp))
                    .border(
                        width = 1.5.dp,
                        color = Color(94, 144, 255, 255),
                        shape = RoundedCornerShape(20.dp)
                    ),
                colors = ButtonDefaults.filledTonalButtonColors(
                    containerColor = Color.White
                )
            ) {
                Text(
                    text = "รายละเอียดเพิ่มเติม",
                    color = Color(94, 144, 255, 255),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Column(
            modifier = Modifier.padding(10.dp).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row (
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                horizontalArrangement = Arrangement.Start
            ){
                Text(
                    text = "ติดต่อเรา",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Row (
                modifier = Modifier.fillMaxWidth().padding(10.dp)
                    .background(color = Color(172, 198, 255, 255))
                    .clip(RoundedCornerShape(10.dp)),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text("Logo")

                Column(
                    modifier = Modifier.padding(vertical = 15.dp)
                ) {
                    Row (
                        modifier = Modifier.padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(
                            imageVector = Icons.Default.Call,
                            contentDescription = null,
                            modifier = Modifier.padding(end = 5.dp)
                        )

                        Text("081-234-5678", fontWeight = FontWeight.SemiBold)
                    }

                    Row (
                        modifier = Modifier.padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = null,
                            modifier = Modifier.padding(end = 5.dp)
                        )

                        Text("Kanchanarat@gmail.com", fontWeight = FontWeight.SemiBold)
                    }

                    Row (
                        modifier = Modifier.padding(10.dp),
                    ){
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            modifier = Modifier.padding(end = 5.dp)
                        )

                        Text(
                            text = "กาญจนารัตน์ เพลส\n" +
                                    "เลขที่ 140/109 หมู่ที่ 14\n" +
                                    "ถ.มิตรภาพ\n" +
                                    "ต.ในเมือง อ.เมืองขอนแก่น\n" +
                                    "จ.ขอนแก่น 40000",
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
        Spacer(Modifier.height(50.dp))
    }
}