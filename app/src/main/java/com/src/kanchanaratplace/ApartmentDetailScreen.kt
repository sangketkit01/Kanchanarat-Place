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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
fun ApartmentDetailScreen(navController : NavHostController){
    val scrollState = rememberScrollState()

    Column (
        modifier = Modifier.fillMaxSize()
            .verticalScroll(scrollState).padding(vertical = 100.dp)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            IconButton(
                onClick = {
                   navController.navigate(Screen.First.route)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null
                )
            }


            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = "รายละเอียด",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Row (
            modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.End
        ){
            FilledTonalButton(
                onClick = {
                    navController.navigate(Screen.AvailableRoom.route)
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
                    text = "ดูห้องพักที่ว่าง",
                    color = Color(94, 144, 255, 255),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        LazyRow(
            modifier = Modifier.padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(horizontal = 10.dp)
        ) {
            val images = listOf(
                R.drawable.example_pic1,
                R.drawable.example_pic1,
                R.drawable.example_pic1,
                R.drawable.example_pic1
            )

            items(images) { imageRes ->
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .width(305.dp)
                        .height(195.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "ข้อมูลเบื้องต้น",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.align(alignment = Alignment.Start)
                .padding(horizontal = 20.dp, vertical = 10.dp)
        )

        Column (
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .clip(RoundedCornerShape(10.dp))
                .border(
                    width = 1.5.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(10.dp)
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(vertical = 5.dp, horizontal = 30.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Column {
                    Text("ประเภท")
                    Text(
                        text = "อพาร์ทเม้นท์",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(vertical = 5.dp, horizontal = 30.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Column {
                    Text("จำนวนชั้น")
                    Text(
                        text = "5 ชั้น",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                }

                Column {
                    Text("จำนวนห้อง")
                    Text(
                        text = "45 ห้อง",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(vertical = 5.dp, horizontal = 30.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Column {
                    Text("ระยะสัญญาขั้นต่ำ")
                    Text(
                        text = "6 เดือน",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                }

                Column {
                    Text("ลิฟต์")
                    Text(
                        text = "มี",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(vertical = 5.dp, horizontal = 30.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Column {
                    Text("สูบบุหรี่ในห้อง")
                    Text(
                        text = "ไม่อนุญาต",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                }

                Column {
                    Text("สัตว์เลี้ยง")
                    Text(
                        text = "ไม่อนุญาต",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "รายละเอียดราคา",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.align(alignment = Alignment.Start)
                .padding(horizontal = 20.dp, vertical = 10.dp)
        )

        Column (
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .clip(RoundedCornerShape(10.dp))
                .border(
                    width = 1.5.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(10.dp)
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(vertical = 5.dp, horizontal = 30.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "รายเดือน",
                    fontSize = 16.sp
                )

                Text(
                    text = "4,000 บาท",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(vertical = 5.dp, horizontal = 30.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "เงินมัดจำ/ประกัน",
                    fontSize = 16.sp
                )

                Text(
                    text = "4,000 บาท",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(vertical = 5.dp, horizontal = 30.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "จ่ายล่วงหน้า",
                    fontSize = 16.sp
                )

                Text(
                    text = "4,000 บาท",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(vertical = 5.dp, horizontal = 30.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "ค่าสวนกลาง",
                    fontSize = 16.sp
                )

                Text(
                    text = "ไม่มี",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(vertical = 5.dp, horizontal = 30.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "ค่าน้ำ",
                    fontSize = 16.sp
                )

                Text(
                    text = "20 บาท/หน่วย",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(vertical = 5.dp, horizontal = 30.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "ค่าไฟ",
                    fontSize = 16.sp
                )

                Text(
                    text = "8 บาท/หน่วย",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "สิ่งอำนวยความสะดวก",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.align(alignment = Alignment.Start)
                .padding(horizontal = 20.dp, vertical = 10.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clip(RoundedCornerShape(10.dp))
                .border(1.dp, Color.Black, RoundedCornerShape(10.dp)),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )

        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                FeatureSection("ส่วนกลาง", listOf("เครื่องซักผ้าหยอดเหรียญ", "ลานจอดรถ"))
                FeatureSection("ระบบรักษาความปลอดภัย", listOf("กล้องวงจรปิด (CCTV)", "ระบบ Key Card"))
                FeatureSection("ภายในห้อง", listOf("ตู้เสื้อผ้า", "เตียงเดี่ยว", "โต๊ะอ่านหนังสือ", "ทีวี", "ตู้เย็น", "เครื่องทำน้ำอุ่น", "Wifi", "โต๊ะเครื่องแป้ง"))
            }
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Composable
fun FeatureSection(title: String, items: List<String>) {
    Column(modifier = Modifier.padding(top = 8.dp)) {
        Text(text = title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(4.dp))
        items.forEach {
            Text(
                text = "• $it" ,
                fontSize = 18.sp,
                modifier = Modifier.padding(start = 35.dp)
            )
        }
    }
}