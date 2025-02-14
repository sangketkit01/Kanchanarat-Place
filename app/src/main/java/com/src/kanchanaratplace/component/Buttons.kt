package com.src.kanchanaratplace.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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