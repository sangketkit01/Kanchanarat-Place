package com.src.kanchanaratplace.component

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.src.kanchanaratplace.R
import com.src.kanchanaratplace.data_util.Payment

@SuppressLint("DefaultLocale")
@Composable
fun FeeDetail(navController : NavHostController ,name : String? ,
               title : String?,paymentList : List<Payment>,
              before : String? , next : String?){

    var totalPrice : Int = 0
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
                    painter = painterResource(id= R.drawable.profile_reservation),
                    contentDescription = null,
                    modifier = Modifier.width(63.dp).height(63.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "คุณ$name",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Text(
                text = "$title",
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
            paymentList.forEach{payment->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text(
                        text = payment.detail,
                    )

                    Text(
                        text = String.format("%,d",payment.price)
                    )
                }

                totalPrice += payment.price
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
                    text = String.format("%,d",totalPrice),
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
                    text = String.format("%,d",totalPrice),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

        }

    }

    Spacer(modifier = Modifier.height(30.dp))
    Column (
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        WhiteBlueButton(
            text = "กลับไปหน้าก่อนหน้านี้",
            onClick = {
                if (before != null) {
                    navController.navigate(before)
                }
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

        BlueWhiteButton(
            text = "ดำเนินการชำระเงิน",
            onClick = {
                if (next != null) {
                    navController.navigate(next)
                }
            }
        )
    }
}