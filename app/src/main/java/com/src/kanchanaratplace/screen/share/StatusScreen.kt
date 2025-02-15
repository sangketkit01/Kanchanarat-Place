package com.src.kanchanaratplace.screen.share

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.src.kanchanaratplace.component.BaseScaffold
import com.src.kanchanaratplace.component.BlueWhiteButton
import com.src.kanchanaratplace.component.CardStatus
import com.src.kanchanaratplace.component.SampleScaffold
import com.src.kanchanaratplace.component.WhiteBlueButton
import com.src.kanchanaratplace.navigation.Screen
import com.src.kanchanaratplace.screen.reservation.PayReservationScreen

@Composable
fun StatusScaffold(navController: NavHostController){
    SampleScaffold(navController,"ชำระค่าบริการ") {
        StatusScreen(navController)
    }
}

@Composable
fun StatusScreen(navController : NavHostController){

    val previousRoute = navController.previousBackStackEntry?.savedStateHandle?.get<String>("previous_route")

    var before: String? = null
    var next : String? = null

    var title : String? = null
    var content : String? = null
    var beforeTextButton : String? = null
    var nextTextButton : String? = null
    var extra : Boolean = false


    when(previousRoute){
        Screen.PayReservation.route -> {
            before = Screen.ReservationStatus.route
            next = Screen.First.route
            title = "ชำระเงินเรียบร้อย"
            content = "เราได้นำเนินการแจ้งหอพักเรียบร้อย\n" +
                    "รอดำเนินการในขั้นต่อไป"
            beforeTextButton = "ตรวจสอบการดำเนินการ"
            nextTextButton = "กลับไปที่หน้าหลัก"
        }

        Screen.ContractFeeDetail.route -> {
            before = Screen.First.route
            next = Screen.Login.route
            title = "ชำระเงินเรียบร้อย"
            content = "ดำเนินการเสร็จสิ้น \n" +
                    "เจ้าของหอพักจะดำเนินการส่ง\n" +
                    "ชื่อผู้ใช้และรหัสผ่านให้ทางไลน์"
            beforeTextButton = "กลับไปที่หน้าหลัก"
            nextTextButton = "เข้าสู่ระบบ"
            extra = true
        }

        Screen.CheckReservation.route-> {
            before = Screen.CheckReservation.route
            next = Screen.ReservationStatus.route
            title = "ดำเนินการเสร็จสิ้น"
            content = "พบข้อมูลการจองของท่าน\n" +
                    "กดปุ่มไปที่หน้าขั้นตอนการดำเนินการ\n" +
                    "เพื่อตรวจสอบสถานะการดำเนินการของท่าน"
            beforeTextButton = "กลับไปหน้าตรวจสอบข้อมูล"
            nextTextButton = "ไปที่หน้าขั้นตอนการดำเนินการ"
        }
    }

    val scrollState = rememberScrollState()

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 40.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        CardStatus(
            status = "Successful",
            title = title ?: "",
            content = content ?: ""
        )

        if(extra){
            Row (
                modifier = Modifier.fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ){
                TextButton(
                    onClick = {
                        navController.navigate(Screen.ApartmentContract.route)
                    }
                ) {
                    Text(
                        text = "ช่องทางการติดต่อเจ้าของหอพัก",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(94, 144, 255, 255),
                        textDecoration = TextDecoration.Underline
                    )
                }
            }
        }

        Column (
            modifier = Modifier.fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            WhiteBlueButton(
                text = beforeTextButton ?: "",
                onClick = {
                    if (before != null) {
                        navController.navigate(before)
                    }
                }
            )

            Spacer(modifier = Modifier.height(10.dp))


            BlueWhiteButton(
                text = nextTextButton ?: "",
                onClick = {
                    if (next != null) {
                        navController.navigate(next)
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}