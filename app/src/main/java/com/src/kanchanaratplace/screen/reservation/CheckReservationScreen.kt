package com.src.kanchanaratplace.screen.reservation

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.src.kanchanaratplace.api.RoomAPI
import com.src.kanchanaratplace.api_util.checkReservationUtility
import com.src.kanchanaratplace.component.BaseScaffold
import com.src.kanchanaratplace.component.MytextField
import com.src.kanchanaratplace.data.Reservation
import com.src.kanchanaratplace.navigation.Screen
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun CheckReservationScaffold(navController: NavHostController){
    BaseScaffold(navController) {
        CheckReservationScreen(navController)
    }
}

@Composable
fun CheckReservationScreen(navController : NavHostController){
    val scrollState = rememberScrollState()

    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    val context = LocalContext.current

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(vertical = 40.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        Card (
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(20.dp),
            border = BorderStroke(
                width = 1.dp,
                color = Color.LightGray,
            )
        ){
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ){
                Text(
                    text = "Kanchanarat Place",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(94, 144, 255, 255)
                )

                MytextField(
                    value = name,
                    onValueChange = {name = it},
                    labelText = "ชื่อ นามสกุล"
                )

                MytextField(
                    value = phone,
                    onValueChange = {phone = it},
                    labelText = "เบอร์โทรศัพท์"
                )

                Button(
                    onClick = {
                        navController.currentBackStackEntry?.savedStateHandle?.set(
                            "previous_route" , Screen.CheckReservation.route
                        )

                        navController.currentBackStackEntry?.savedStateHandle?.set(
                            "before", Screen.CheckReservation.route
                        )
                        navController.currentBackStackEntry?.savedStateHandle?.set(
                            "next", Screen.First.route
                        )


                        checkReservationUtility(
                            name,
                            phone,
                            onResponse = {response->
                                navController.currentBackStackEntry?.savedStateHandle?.set(
                                    "reservation_data" , response
                                )

                                navController.navigate(Screen.Status.route)
                            },
                            onElse = {

                            },
                            onFailure = {t->
                                Toast.makeText(context,"Error Check LogCat",Toast.LENGTH_SHORT).show()
                                t.message?.let { Log.e("Error",it) }
                            }
                        )

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(82, 146, 255),  // สีฟ้า
                        disabledContainerColor = Color(82, 146, 255).copy(alpha = 0.5f)
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = "ตรวจสอบข้อมูล",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}