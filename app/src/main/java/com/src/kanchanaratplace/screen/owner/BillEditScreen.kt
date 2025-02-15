package com.src.kanchanaratplace.screen.owner

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.src.kanchanaratplace.component.SampleScaffold

@Composable
fun BillEditScaffold(navController: NavHostController){
    SampleScaffold(navController,"ห้อง 101") {
        BillEditScreen(navController)
    }
}

@Composable
fun BillEditScreen(navController : NavHostController){
    val scrollState = rememberScrollState()

    Column (
        modifier = Modifier.fillMaxSize()
            .verticalScroll(scrollState)
            .background(Color(240, 240, 240, 255)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        Card (
            modifier = Modifier.fillMaxWidth()
                .padding(10.dp),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                contentColor = Color.White
            )
        ){
            Column (
                modifier = Modifier.fillMaxWidth()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ){
                Row (
                    modifier = Modifier.fillMaxWidth()
                        .background(Color(240, 240, 240, 255))
                        .clip(RoundedCornerShape(10.dp)),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ){  }
            }
        }
    }
}