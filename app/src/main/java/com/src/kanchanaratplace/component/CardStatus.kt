package com.src.kanchanaratplace.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.src.kanchanaratplace.R

@Composable
fun CardStatus(status : String,title : String , content : String,err : Boolean){
    Card (
        modifier = Modifier.fillMaxWidth()
            .padding(20.dp)
            .height(335.dp),
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(
            width = 1.dp,
            color = Color.LightGray
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ){
        Column (
            modifier = Modifier.fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Icon(
                painter = painterResource(
                    id = if (!err) R.drawable.check_green
                    else R.drawable.notfound
                ),
                contentDescription = null,
                modifier = Modifier.width(80.dp).height(80.dp)
                    .padding(bottom = 10.dp),
                tint = if (!err) Color(50, 161, 41, 255)
                else Color(251, 35, 35, 255)
            )

            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 10.dp)
            )

            Text(
                text = content,
                fontSize = 15.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 40.dp),
                color = Color(128, 128, 128, 255)
            )
        }
    }
}