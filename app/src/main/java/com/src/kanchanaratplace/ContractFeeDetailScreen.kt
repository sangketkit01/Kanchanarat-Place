package com.src.kanchanaratplace

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
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
fun ContractFeeDetailScreen(navController : NavHostController){
    val scrollState = rememberScrollState()
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(vertical = 80.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        Spacer(modifier = Modifier.height(40.dp))
        Card (
            modifier = Modifier.padding(20.dp).clip(RoundedCornerShape(20.dp))
                .border(
                    width = 1.dp,
                    color = Color.Gray,
                    shape = RoundedCornerShape(20.dp)
                ),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ){
            Column (
                modifier = Modifier.padding(20.dp)
                    .fillMaxWidth()
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Image(
                        painter = painterResource(id=R.drawable.profile_reservation),
                        contentDescription = null,
                        modifier = Modifier.width(63.dp).height(63.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "คุณใจดี ดีใจจัง",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Text(
                    text = "รายละเอียดสัญญา",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 20.dp)
                )
            }

            HorizontalDivider(modifier = Modifier.fillMaxWidth())

            Column (
                modifier = Modifier.padding(20.dp)
                    .fillMaxWidth()
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text(
                        text = "ราคาห้อง",
                    )

                    Text(
                        text = "4,000 บาท",
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text(
                        text = "จ่ายล่วงหน้า"
                    )

                    Text(
                        text = "4,000 บาท"
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text(
                        text = "เงินมัดจำส่วนที่เหลือ"
                    )

                    Text(
                        text = "3,000 บาท"
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text(
                        text = "รวมทั้งหมด",
                        fontWeight = FontWeight.SemiBold
                    )

                    Text(
                        text = "11,000 บาท",
                        fontWeight = FontWeight.SemiBold
                    )
                }

            }

            HorizontalDivider(modifier = Modifier.fillMaxWidth())

            Column (
                modifier = Modifier.padding(20.dp)
                    .fillMaxWidth()
            ){

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text(
                        text = "ยอดที่ต้องชำระทั้งหมด",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Text(
                        text = "11,000 บาท",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

            }

        }

        Spacer(modifier = Modifier.height(30.dp))
        Column (
            modifier = Modifier.fillMaxWidth(),
        ){
            FilledTonalButton(
                onClick = {
                    navController.navigate(Screen.ReservationStatus.route)
                },
                colors = ButtonDefaults.filledTonalButtonColors(
                    containerColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)
                    .height(47.dp)
                    .border(
                        width = 1.5.dp,
                        color = Color(94, 144, 255, 255),
                        shape = RoundedCornerShape(50.dp)
                    )
            ) {
                Text(
                    text = "กลับไปหน้าก่อนหน้านี้",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(94, 144, 255, 255)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            FilledTonalButton(
                onClick = {
                    navController.navigate(Screen.ContractPayQR.route)
                },
                colors = ButtonDefaults.filledTonalButtonColors(
                    containerColor = Color(94, 144, 255, 255)
                ),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp).height(47.dp)
            ) {
                Text(
                    text = "ดำเนินการชำระเงิน",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(70.dp))

    }
}