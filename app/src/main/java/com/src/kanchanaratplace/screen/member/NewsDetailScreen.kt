package com.src.kanchanaratplace.screen.member

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.src.kanchanaratplace.R
import com.src.kanchanaratplace.component.NewsHeader
import com.src.kanchanaratplace.component.SampleScaffold

@Composable
fun NewsDetailScaffold(navController: NavHostController){
    SampleScaffold(navController,"ข่าวสาร") {
        NewsDetailScreen(navController)
    }
}

@Composable
fun NewsDetailScreen(navController : NavHostController){
    var comment by remember { mutableStateOf("") }

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
                .padding(20.dp),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(
                width = 1.dp,
                color = Color.LightGray
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ){
            Column(
                modifier = Modifier.fillMaxSize()
            ){
                NewsHeader()

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

                Spacer(modifier = Modifier.height(10.dp))

                Row (
                    modifier = Modifier.fillMaxWidth()
                        .background(Color(217, 217, 217, 255))
                        .padding(10.dp)
                ){
                    Text(
                        text = "2 ความคิดเห็น",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(112, 110, 110, 255)
                    )
                }

                Row (
                    modifier = Modifier.fillMaxWidth()
                        .padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ){
                    Image(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.padding(start = 20.dp)
                            .size(40.dp)
                    )

                    Column(
                        horizontalAlignment = Alignment.Start
                    ){
                        Text(
                            text = "มณีรัตน์ 102",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        Text(
                            text = "ถึงเวลากี่โมงคะ",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(163, 163, 163, 255)
                        )
                    }
                }

                HorizontalDivider(thickness = 1.dp)

                Row (
                    modifier = Modifier.fillMaxWidth()
                        .padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ){
                    Image(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.padding(start = 20.dp)
                            .size(40.dp)
                    )

                    Column(
                        horizontalAlignment = Alignment.Start
                    ){
                        Text(
                            text = "มณีรัตน์ 102",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        Text(
                            text = "ถึงเวลากี่โมงคะ",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(163, 163, 163, 255)
                        )
                    }
                }

                HorizontalDivider(thickness = 1.dp)

                Spacer(modifier = Modifier.height(60.dp))

                OutlinedTextField(
                    value = comment,
                    onValueChange = {newValue->
                        comment = newValue
                    },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.comment),
                            contentDescription = null,
                            tint = Color(216, 216, 216, 255),
                            modifier = Modifier.size(30.dp)
                        )
                    },
                    placeholder = { Text("แสดงความคิดเห็น") },
                    trailingIcon = {
                        IconButton(
                            onClick = {

                            }
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.send),
                                contentDescription = null,
                                tint = Color(216, 216, 216, 255),
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    },
                    colors = TextFieldDefaults.colors(
                        focusedPlaceholderColor = Color(216, 216, 216, 255),
                        unfocusedPlaceholderColor = Color(216, 216, 216, 255),
                        focusedIndicatorColor = Color.LightGray,
                        unfocusedIndicatorColor = Color.LightGray,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth().padding(20.dp)
                        .clip(RoundedCornerShape(20.dp)),
                    shape = RoundedCornerShape(20.dp),
                )
            }
        }
    }
}