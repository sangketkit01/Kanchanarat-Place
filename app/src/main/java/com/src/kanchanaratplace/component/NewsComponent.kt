package com.src.kanchanaratplace.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.src.kanchanaratplace.R

@Composable
fun NewsHeader(){
    Row (
        modifier = Modifier.fillMaxWidth()
            .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ){
        Image(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(55.dp)
        )

        Column (
            horizontalAlignment = Alignment.Start
        ){
            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Kanchanarat",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )

                Text(
                    text = "(เจ้าของหอพัก)",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(163, 163, 163, 255)
                )
            }

            Text(
                text = "1 วันที่แล้ว",
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(163, 163, 163, 255)
            )
        }
    }
}

@Composable
fun NewsBottom(){
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(vertical = 5.dp, horizontal = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ){
            Image(
                painter = painterResource(R.drawable.hearth),
                contentScale = ContentScale.Fit,
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )

            Text(
                text = "ถูกใจ",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(163, 163, 163, 255)
            )
        }

        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ){
            Image(
                painter = painterResource(R.drawable.comment),
                contentScale = ContentScale.Fit,
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )

            Text(
                text = "แสดงความคิดเห็น",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(163, 163, 163, 255)
            )
        }
    }
}