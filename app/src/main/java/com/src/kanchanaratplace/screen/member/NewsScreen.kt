package com.src.kanchanaratplace.screen.member

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.src.kanchanaratplace.R
import com.src.kanchanaratplace.component.NewsBottom
import com.src.kanchanaratplace.component.NewsHeader
import com.src.kanchanaratplace.component.SampleScaffold
import com.src.kanchanaratplace.navigation.Screen

@Composable
fun NewsScaffold(navController: NavHostController){
    SampleScaffold(navController,"ข่าวสาร") {
        NewsScreen(navController)
    }
}

@Composable
fun NewsScreen(navController : NavHostController){
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
        Card (
            modifier = Modifier.fillMaxWidth()
                .padding(20.dp)
                .clickable {
                    navController.navigate(Screen.NewsDetail.route)
                },
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(
                width = 1.dp,
                color = Color.LightGray
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            )
        ){
            NewsHeader()

            HorizontalDivider(thickness = 1.dp)

            Column(
                modifier = Modifier.fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.Start
            ){
                Text(
                    text = "แจ้งการทำความสะอาดบริเวณลานจอดรถ...",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Start
                )

                Text(
                    text = "ขอความร่วมมือลูกหอทุกท่าน ผู้ที่จอดมอเตอร์ไซค์ในที่จอดของหอพัก วันที่ 1 มกราคม 2568 " +
                            "จะมีการทำความสะอาดบริเวณที่จอดรถ ขอให้ลูกหอทุกท่านไม่ต้องล็อคคอรถนะคะ " +
                            "เพื่อให้สะดวกต่อการเคลื่อนย้ายระหว่างทำความสะอาด ขอบคุณค่ะ",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Start,
                    color = Color(163, 163, 163, 255)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            HorizontalDivider()

            NewsBottom()
        }

        Card (
            modifier = Modifier.fillMaxWidth()
                .padding(20.dp)
                .clickable {
                    navController.navigate(Screen.NewsDetail.route)
                },
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(
                width = 1.dp,
                color = Color.LightGray
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            )
        ){
            NewsHeader()

            HorizontalDivider(thickness = 1.dp)

            Column(
                modifier = Modifier.fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.Start
            ){
                Image(
                    painter = painterResource(R.drawable.qrcode),
                    contentScale = ContentScale.Fit,
                    contentDescription = null,
                    modifier = Modifier.padding(10.dp)
                        .size(150.dp).align(Alignment.CenterHorizontally)
                )

                Text(
                    text = "เปลี่ยนแปลงบัญชีสำหรับชำระค่าเช่า...",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Start

                )

                Text(
                    text = "ขออนุญาตแจ้งลุกหอทุกคนถึงการชำระบิลค่าห้อง ตั้งแต่วันที่ 1 มกราคม 2567 " +
                            "เป็นต้นไปทางหอขอเปลี่ยนบัญชีการค่าเช่า เป็นบัญชีกรุงไทย... ",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Start,
                    color = Color(163, 163, 163, 255)
                )
            }

            HorizontalDivider(thickness = 1.dp)

            NewsBottom()
        }
    }
}