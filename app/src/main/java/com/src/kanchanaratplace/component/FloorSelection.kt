package com.src.kanchanaratplace.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FloorSelection(selectedFloor: Int, onFloorSelected: (Int) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .background(Color(240, 240, 240, 255)),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("ชั้น: ", fontSize = 15.sp, fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(start = 10.dp))

        for (floor in 1..5) {
            Button(
                onClick = { onFloorSelected(floor) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedFloor == floor) {
                        Color(94, 144, 255, 255)
                    } else {
                        Color.Transparent
                    }
                ),
                modifier = Modifier.padding(horizontal = 4.dp)
            ) {
                Text(
                    text = "$floor",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = if (selectedFloor == floor) {
                        Color.White
                    } else {
                        Color.Black
                    }
                )
            }
        }
    }
}
