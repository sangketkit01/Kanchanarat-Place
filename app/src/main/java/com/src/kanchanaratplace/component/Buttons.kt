package com.src.kanchanaratplace.component

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WhiteBlueButton(text : String , onClick : ()-> Unit){
    FilledTonalButton(
        onClick = onClick,
        colors = ButtonDefaults.filledTonalButtonColors(
            containerColor = Color.White
        ),
        modifier = Modifier
            .border(
                width = 1.5.dp,
                color = Color(94, 144, 255, 255),
                shape = RoundedCornerShape(50.dp)
            )
            .width(347.dp).height(47.dp)
    ) {
        Text(
            text = text,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(94, 144, 255, 255)
        )
    }
}

@Composable
fun BlueWhiteButton(text : String, onClick: () -> Unit){
    FilledTonalButton(
        onClick = onClick,
        colors = ButtonDefaults.filledTonalButtonColors(
            containerColor = Color(94, 144, 255, 255)
        ),
        modifier = Modifier.width(347.dp).height(47.dp)
            .border(
                width = 1.5.dp,
                color = Color.Transparent,
                shape = RoundedCornerShape(50.dp)
            )
    ) {
        Text(
            text = text,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )
    }
}

@Composable
fun SmallWhiteBlueButton(text : String, onClick: () -> Unit){
    FilledTonalButton(
        onClick = onClick,
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
            text = text,
            color = Color(94, 144, 255, 255),
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun SmallBlueWhiteButton(text: String , onClick: () -> Unit){
    FilledTonalButton(
        onClick = onClick,
        colors = ButtonDefaults.filledTonalButtonColors(
            containerColor = Color(94, 144, 255, 255)
        ),
        modifier = Modifier.clip(RoundedCornerShape(20.dp))
            .border(
                width = 1.5.dp,
                color = Color.Transparent,
                shape = RoundedCornerShape(50.dp)
            )
    ) {
        Text(
            text = text,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )
    }
}

@Composable
fun ButtonWithBadge(text: String, badgeCount: Int, modifier: Modifier = Modifier,
                    background : Color,onClick: () -> Unit) {
    val contextForToast = LocalContext.current.applicationContext
    Button(
        onClick = onClick,
        modifier = modifier
            .height(50.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = background) // ปุ่มสีขาว
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = text,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            if (badgeCount > 0) {
                Spacer(modifier = Modifier.width(6.dp))
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .background(Color.Red, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = badgeCount.toString(),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}