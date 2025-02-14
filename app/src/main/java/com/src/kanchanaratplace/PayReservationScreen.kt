package com.src.kanchanaratplace

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.src.kanchanaratplace.data.MakeReservation
import com.src.kanchanaratplace.data_util.Payment
import com.src.kanchanaratplace.navigation.Screen
import com.src.kanchanaratplace.Component.FeeDetail

@Composable
fun PayReservationScreen(navController : NavHostController){
    val data = navController.previousBackStackEntry?.savedStateHandle?.get<MakeReservation>("data")
    val scrollState = rememberScrollState()

    val paymentList = listOf<Payment>(
        Payment(null,null,"เงินมัดจำ/ประกัน",1000,null)
    )

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

        navController.currentBackStackEntry?.savedStateHandle?.set(
            "before",Screen.PayReservation.route
        )
        navController.currentBackStackEntry?.savedStateHandle?.set(
            "next",Screen.ReservationPaymentStatus.route
        )

        FeeDetail(
            navController = navController,
            name = data?.name,
            title = "รายละเอียดการจอง",
            paymentList = paymentList,
            Screen.ReservationDetail.route,
            Screen.QrCode.route
        )

        Spacer(modifier = Modifier.height(70.dp))

    }
}