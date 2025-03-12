package com.src.kanchanaratplace.screen.owner

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.src.kanchanaratplace.api_util.approveBillUtility
import com.src.kanchanaratplace.component.SampleScaffold
import com.src.kanchanaratplace.component.SlipImageDialog
import com.src.kanchanaratplace.data.Bill
import com.src.kanchanaratplace.data.Member
import com.src.kanchanaratplace.data.Rooms
import com.src.kanchanaratplace.data_util.Receipt
import com.src.kanchanaratplace.navigation.Screen
import com.src.kanchanaratplace.status.OtherStatus
import java.util.Calendar


@Composable
fun CheckMemberPaymentDetailScaffold(navController: NavHostController){
    SampleScaffold(navController,"การชำระเงิน"){
        CheckMemberPaymentDetailScreen(navController)
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun CheckMemberPaymentDetailScreen(navController : NavHostController){
    val roomData = navController.previousBackStackEntry?.savedStateHandle?.get<Rooms>("room_data")
    val billData = navController.previousBackStackEntry?.savedStateHandle?.get<Bill?>("bill_data")
    val memberData = navController.previousBackStackEntry?.savedStateHandle?.get<Member?>("member_data")

    val waterBill = if((billData?.waterUsed ?: 0) <= 5) 150
                    else 150 + (((billData?.waterUsed ?: 0) - 5) * 20)

    val electricityBill = (billData?.electricityUsed ?: 0) * 8

    var dateText by remember { mutableStateOf("") }

    val context = LocalContext.current

    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    var slipAlert by remember { mutableStateOf(false) }
    var confirmAlert by remember { mutableStateOf(false) }

    @SuppressLint("DefaultLocale")
    fun showDatePicker() {
        DatePickerDialog(
            context,
            { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
                dateText = String.format("%02d/%02d/%04d", selectedDay, selectedMonth + 1, selectedYear)
            },
            year,
            month,
            day
        ).show()
    }

    val scrollState = rememberScrollState()

    Column (
        modifier = Modifier.fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(Modifier.height(10.dp))

        Text(
            text = "ห้อง ${roomData?.code ?: ""}",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(20.dp)
        )

        Column(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            HorizontalDivider(
                thickness = 0.5.dp ,
                color = Color.LightGray ,
            )

            Column(modifier = Modifier.fillMaxWidth().padding(10.dp)){
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = "เลขที่สัญญา",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )

                    Text(
                        text = "00001",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(2f),
                        color = Color(0xFF808080)
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ){
                        Text(
                            text = "วันที่",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = billData?.createdAt.toString().split(" ")[0],
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF808080)
                        )
                    }
                }

                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = "ผู้เช่า",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                    )

                    Text(
                        text = memberData?.name ?: "Unknown",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF808080)
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth()
                    .background(Color(0xFFD9D9D9))
                    .padding(vertical = 5.dp, horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = "รายการ",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "จำนวนเงิน/บาท",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = "ค่าเช่าห้อง",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF808080)
                )

                Text(
                    text = "4000",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF808080)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = "ค่าไฟ",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF808080)
                )

                Text(
                    text = "$electricityBill",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF808080)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = "ค่าน้ำ",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF808080)
                )

                Text(
                    text = "$waterBill",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF808080)
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Column{
                Text(
                    text = "ผู้เช่า",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 5.dp)
                )

                OutlinedTextField(
                    value = memberData?.name ?: "Unknown",
                    onValueChange = {},
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.LightGray,
                        unfocusedIndicatorColor = Color.LightGray,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    ),
                    modifier = Modifier.padding(bottom = 10.dp)
                        .width(200.dp),
                    readOnly = true
                )

                Text(
                    text = "ค่าใช้จ่ายทั้งหมด",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 5.dp)
                )

                val formattedPrice = billData?.totalPrice?.let {
                    String.format("%,d", it)
                }

                formattedPrice?.let {
                    OutlinedTextField(
                        value = it,
                        onValueChange = {},
                        shape = RoundedCornerShape(10.dp),
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.LightGray,
                            unfocusedIndicatorColor = Color.LightGray,
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White
                        ),
                        modifier = Modifier
                            .padding(bottom = 10.dp)
                            .width(200.dp),
                        readOnly = true
                    )
                }

                Text(
                    text = "วันที่รับเงิน",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 5.dp)
                )

                OutlinedTextField(
                    value = dateText,
                    onValueChange = { dateText = it },
                    readOnly = true,
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.LightGray,
                        unfocusedIndicatorColor = Color.LightGray,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    ),
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .width(200.dp),
                    trailingIcon = {
                        IconButton(onClick = { showDatePicker() }) {
                            Icon(
                                imageVector = Icons.Default.DateRange,
                                contentDescription = "Select Date"
                            )
                        }
                    }
                )

                Text(
                    text = "ผู้รับเงิน",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 5.dp)
                )

                OutlinedTextField(
                    value = "สุวรรณี ลาทอง",
                    onValueChange = {},
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.LightGray,
                        unfocusedIndicatorColor = Color.LightGray,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    ),
                    modifier = Modifier.padding(bottom = 10.dp)
                        .width(200.dp),
                    readOnly = true
                )
            }

            if(billData?.slipPath != null){
                Image(
                    painter = rememberAsyncImagePainter(
                        model = billData.slipPath.replace(
                            "../uploads/","http://10.0.2.2:3000/uploads/"
                        )
                    ),
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    modifier = Modifier.width(180.dp).height(325.dp)
                        .clickable { slipAlert = true }
                )
            }else{
               Text(
                   text = "ยังไม่สลิป",
                   fontSize = 18.sp,
                   fontWeight = FontWeight.Bold
               )
            }
        }

        if(billData?.statusId == OtherStatus.SUCCESS.id){
            Button(
                onClick = {
                    val receipt = listOf<Receipt>(
                        Receipt("ค่าเช่าห้อง",4000),
                        Receipt("ค่าไฟ",electricityBill),
                        Receipt("ค่าน้ำ",waterBill)
                    )

                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        "receipt_data",receipt
                    )

                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        "total_price",billData.totalPrice
                    )

                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        "name",memberData?.name
                    )

                    navController.navigate(Screen.Receipt.route)
                },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = Color(0xFF5E90FF)
                ),
                modifier = Modifier.width(150.dp).height(45.dp)
            ){
                Text(
                    text = "พิมพ์ใบเสร็จ",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        }else if(billData?.slipPath != null){
            Button(
                onClick = {
                    confirmAlert = true
                },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = Color(0xFF32A129)
                ),
                modifier = Modifier.width(150.dp).height(45.dp)
            ){
                Text(
                    text = "รับเงิน",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
    }

    if(slipAlert){
        SlipImageDialog(
            billData?.slipPath?.replace("../uploads/","http://10.0.2.2:3000/uploads/"),
            onDismiss = {slipAlert = false}
        )
    }

    if(confirmAlert){
        AlertDialog(
            onDismissRequest = {confirmAlert = false},
            title = { Text("ยืนยันรับเงิน") },
            text = { Text("คุณต้องการจะยืนยันรับเงินจากห้อง ${roomData?.code} หรือไม่") },
            confirmButton = {
                TextButton(
                    onClick = {
                        approveBillUtility(
                            billData?.billId ?: 0,
                            onResponse = { response->
                                Toast.makeText(context,"Approve bill successfully",Toast.LENGTH_SHORT)
                                    .show()

                                navController.popBackStack()
                            },
                            onElse = { elseResponse->
                                Toast.makeText(context,"Approve bill successfully",Toast.LENGTH_SHORT)
                                    .show()
                                Log.e("Error",elseResponse.message())
                            },
                            onFailure = { t->
                                Toast.makeText(context,"Error onFailure",Toast.LENGTH_SHORT)
                                    .show()
                                Log.e("Error",t.message ?: "No Message")
                            }
                        )
                    }
                ){
                    Text("ตกลง")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        confirmAlert = false
                    }
                ){
                    Text("ยกเลิก")
                }
            }
        )
    }
}