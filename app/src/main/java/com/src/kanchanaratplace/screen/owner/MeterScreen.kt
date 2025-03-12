package com.src.kanchanaratplace.screen.owner

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.src.kanchanaratplace.component.SampleScaffold
import com.src.kanchanaratplace.navigation.Screen

@Composable
fun MeterScaffold(navController: NavHostController){
    SampleScaffold(navController,"จดมิเตอร์"){
        MeterScreen(navController)
    }
}

@Composable
fun MeterScreen(navController : NavHostController){
    val scrollState = rememberScrollState()

    Column (
        modifier = Modifier.fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                navController.navigate(Screen.MeterWater.route)
            },
            modifier = Modifier.fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 25.dp)
                .height(55.dp),
            shape = RoundedCornerShape(10.dp),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 4.dp
            ),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            )
        ){
            Text(
                "จดมิเตอร์น้ำ",
                fontSize = 18.sp
            )
        }

        Button(
            onClick = {
                navController.navigate(Screen.MeterElectricity.route)
            },
            modifier = Modifier.fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 25.dp)
                .height(55.dp),
            shape = RoundedCornerShape(10.dp),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 4.dp
            ),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            )
        ){
            Text(
                "จดมิเตอร์ไฟฟ้า",
                fontSize = 18.sp
            )
        }
    }
}