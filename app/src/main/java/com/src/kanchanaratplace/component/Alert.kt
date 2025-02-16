package com.src.kanchanaratplace.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage


@Composable
fun LoginRequiredDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = {},
            text = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(15.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Text(
                        text = "กรุณาเข้าสู่ระบบ",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = "ขออภัย หากเกิดข้อผิดพลาด\n" +
                                "ในการเข้าใช้งานบริการ",
                        fontSize = 15.sp,
                        color = Color(128, 128, 128, 255),
                        textAlign = TextAlign.Center
                    )

                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        FilledTonalButton(
                            onClick = {
                                onNavigateToHome()
                            },
                            colors = ButtonDefaults.filledTonalButtonColors(
                                containerColor = Color.White
                            ),
                            modifier = Modifier
                                .border(
                                    width = 1.5.dp,
                                    color = Color(94, 144, 255, 255),
                                    shape = RoundedCornerShape(50.dp)
                                )
                                .width(181.dp)
                                .height(37.dp)
                        ) {
                            Text(
                                text = "กลับไปที่หน้าหลัก",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(94, 144, 255, 255)
                            )
                        }

                        FilledTonalButton(
                            onClick = {
                                onNavigateToLogin()
                            },
                            colors = ButtonDefaults.filledTonalButtonColors(
                                containerColor = Color(94, 144, 255, 255)
                            ),
                            modifier = Modifier
                                .width(181.dp)
                                .height(37.dp)
                        ) {
                            Text(
                                text = "เข้าสู่ระบบ",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White
                            )
                        }
                    }
                }
            },
            confirmButton = {},
            containerColor = Color.White,
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .border(
                    width = 1.dp,
                    color = Color.LightGray,
                    shape = RoundedCornerShape(20.dp)
                )
        )
    }
}

@Composable
fun UnAuthorizedAlert(show : Boolean , onDismiss: () -> Unit){
    if (show){
        AlertDialog(
            onDismissRequest = onDismiss,
            title = {
                Text("ไม่มีสิทธิ์การเข้าถึง")
            },
            text = {
                Text("ขออภัย เมนูนี้ผู้มีสิทธิ์เข้าถึงมีเพียงเจ้าของหอเท่านั้น")
            },
            confirmButton = {
                TextButton(
                    onClick = onDismiss
                ) {
                    Text("ตกลง")
                }
            }
        )
    }
}

@Composable
fun SlipImageDialog(slipUrl: String?, onDismiss: () -> Unit) {
    val baseUrl = "http://10.0.2.2:3000"

    val slipPath = slipUrl?.replace("../uploads","$baseUrl/uploads")


    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text("สลิปโอนเงิน") },
        text = {
            AsyncImage(
                model = slipPath,
                contentDescription = "Slip Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
        },
        confirmButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("ปิด")
            }
        }
    )
}
