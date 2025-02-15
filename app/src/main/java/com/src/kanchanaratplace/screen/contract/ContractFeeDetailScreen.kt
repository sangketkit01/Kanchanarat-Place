package com.src.kanchanaratplace.screen.contract

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.src.kanchanaratplace.component.BaseScaffold
import com.src.kanchanaratplace.data_util.Payment
import com.src.kanchanaratplace.navigation.Screen
import com.src.kanchanaratplace.component.FeeDetail
import com.src.kanchanaratplace.component.SampleScaffold
import com.src.kanchanaratplace.navigation.AuthenticatedTopBar
import com.src.kanchanaratplace.navigation.BottomBar
import com.src.kanchanaratplace.navigation.UnAuthenticationTopBar
import com.src.kanchanaratplace.screen.main.FirstScreen
import com.src.kanchanaratplace.screen.reservation.ReservationStatusScreen
import com.src.kanchanaratplace.session.MemberSharePreferencesManager


@Composable
fun ContractFeeDetailScaffold(navController : NavHostController){
    SampleScaffold(navController,"ชำระค่าบริการ") {
        ContractFeeDetailScreen(navController)
    }
}

@Composable
fun ContractFeeDetailScreen(navController : NavHostController){
    val scrollState = rememberScrollState()
    val paymentList = listOf<Payment>(
        Payment(null,null,"ราคาห้อง",4000,null),
        Payment(null,null,"จ่ายล่วงหน้า",4000,null),
        Payment(null,null,"เงินมัดจำส่วนที่เหลือ",3000,null),
    )

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        Spacer(modifier = Modifier.height(40.dp))

        navController.currentBackStackEntry?.savedStateHandle?.set(
            "previous_route" , Screen.ContractFeeDetail.route
        )

        navController.currentBackStackEntry?.savedStateHandle?.set(
            "before",Screen.ContractFeeDetail.route
        )
        navController.currentBackStackEntry?.savedStateHandle?.set(
            "next", Screen.Status.route
        )

        FeeDetail(
            navController = navController,
            name = "ใจดี ดีใจจัง",
            title = "รายละเอียดสัญญา",
            paymentList = paymentList,
            Screen.ContractFee.route,
            Screen.QrCode.route
        )
        Spacer(modifier = Modifier.height(40.dp))

    }
}