package com.src.kanchanaratplace.screen.main

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.src.kanchanaratplace.R
import com.src.kanchanaratplace.component.BaseScaffold

@Composable
fun ProfileScaffold(navController : NavHostController){
    BaseScaffold(navController) {
        ProfileScreen(navController)
    }
}

@Composable
fun ProfileScreen(navHostController: NavHostController){
    val context = LocalContext.current.applicationContext
    val scrollState = rememberScrollState()
    Column (
        modifier = Modifier.fillMaxSize()
            .verticalScroll(scrollState).padding(vertical = 120.dp)
            .background(Color.Transparent),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ){
        Card (
            modifier = Modifier.fillMaxWidth()
                .padding(10.dp)
                .clickable {
                    Toast.makeText(context,"Hello",Toast.LENGTH_SHORT).show()
                },
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(1.dp, Color.LightGray),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ){
            Row (
                modifier = Modifier.fillMaxSize()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = null,
                        modifier = Modifier.width(38.dp).height(37.dp)
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = "แก้ไขโปรไฟล์",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                )
            }
        }

        Card (
            modifier = Modifier.fillMaxWidth()
                .padding(10.dp)
                .clickable {
                    Toast.makeText(context,"Hello",Toast.LENGTH_SHORT).show()
                },
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(1.dp, Color.LightGray),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ){
            Row (
                modifier = Modifier.fillMaxSize()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.repair),
                        contentDescription = null,
                        modifier = Modifier.width(38.dp).height(37.dp)
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = "แจ้งซ่อม",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                )
            }
        }

        Card (
            modifier = Modifier.fillMaxWidth()
                .padding(10.dp)
                .clickable {
                    Toast.makeText(context,"Hello",Toast.LENGTH_SHORT).show()
                },
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(1.dp, Color.LightGray),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ){
            Row (
                modifier = Modifier.fillMaxSize()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.bill),
                        contentDescription = null,
                        modifier = Modifier.width(38.dp).height(37.dp)
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = "ดูบิล",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                )
            }
        }

        Card (
            modifier = Modifier.fillMaxWidth()
                .padding(10.dp)
                .clickable {
                    Toast.makeText(context,"Hello",Toast.LENGTH_SHORT).show()
                },
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(1.dp, Color.LightGray),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ){
            Row (
                modifier = Modifier.fillMaxSize()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.payment),
                        contentDescription = null,
                        modifier = Modifier.width(38.dp).height(37.dp)
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = "จ่ายบิล",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                )
            }
        }

        Card (
            modifier = Modifier.fillMaxWidth()
                .padding(10.dp)
                .clickable {
                    Toast.makeText(context,"Hello",Toast.LENGTH_SHORT).show()
                },
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(1.dp, Color.LightGray),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ){
            Row (
                modifier = Modifier.fillMaxSize()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.cleasing),
                        contentDescription = null,
                        modifier = Modifier.width(38.dp).height(37.dp)
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = "บริการทำความสะอาด",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                )
            }
        }

        Card (
            modifier = Modifier.fillMaxWidth()
                .padding(10.dp)
                .clickable {
                    Toast.makeText(context,"Hello",Toast.LENGTH_SHORT).show()
                },
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(1.dp, Color.LightGray),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ){
            Row (
                modifier = Modifier.fillMaxSize()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.check),
                        contentDescription = null,
                        modifier = Modifier.width(38.dp).height(37.dp)
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = "เช็คสถานะ",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                )
            }
        }

        Card (
            modifier = Modifier.fillMaxWidth()
                .padding(10.dp)
                .clickable {
                    Toast.makeText(context,"Hello",Toast.LENGTH_SHORT).show()
                },
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(1.dp, Color.LightGray),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ){
            Row (
                modifier = Modifier.fillMaxSize()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.paper),
                        contentDescription = null,
                        modifier = Modifier.width(38.dp).height(37.dp)
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = "สัญญาหอ",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                )
            }
        }
        Spacer(Modifier.height(40.dp))
    }
}