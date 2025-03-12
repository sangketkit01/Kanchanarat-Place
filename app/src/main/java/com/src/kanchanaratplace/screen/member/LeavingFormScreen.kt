package com.src.kanchanaratplace.screen.member

import android.app.DatePickerDialog
import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.src.kanchanaratplace.api_util.insertLeavingUtility
import com.src.kanchanaratplace.component.SampleScaffold
import com.src.kanchanaratplace.session.MemberSharePreferencesManager
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun LeavingFormScaffold(navController: NavHostController){
    SampleScaffold(navController,"แจ้งย้ายออก"){
        LeavingFormScreen(navController)
    }
}

@Composable
fun LeavingFormScreen(navController: NavHostController) {
    val context = LocalContext.current

    val sharePreferences = remember {MemberSharePreferencesManager(context)}

    val thaiFormatter = remember {
        SimpleDateFormat("d MMMM yyyy", Locale("th", "TH"))
    }

    var reportDate by remember { mutableStateOf("") }
    var moveOutDate by remember { mutableStateOf("") }
    var errorText by remember { mutableStateOf("") }
    var selectedReason by remember { mutableStateOf("") }

    var reportDateDate by remember { mutableStateOf(Date()) }
    var moveOutDateDate by remember { mutableStateOf(Date()) }

    var additionalDetails by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .clip(RoundedCornerShape(10.dp)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(94, 144, 255, 255))
                    .padding(vertical = 20.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "แบบฟอร์มการแจ้งย้ายออก",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }

            fun showDatePicker(onDateSelected: (String) -> Unit) {
                val calendar = Calendar.getInstance()
                DatePickerDialog(
                    context,
                    { _, year, month, dayOfMonth ->
                        val selectedDate = Calendar.getInstance().apply {
                            set(year, month, dayOfMonth)
                        }
                        onDateSelected(thaiFormatter.format(selectedDate.time))
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp) // เว้นระยะห่างระหว่างช่อง
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = "วันที่แจ้ง :", fontWeight = FontWeight.Bold)
                    OutlinedTextField(
                        value = reportDate,
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier.fillMaxWidth(),
                        trailingIcon = {
                            IconButton(onClick = { showDatePicker { reportDate = it } }) {
                                Icon(imageVector = Icons.Default.DateRange, contentDescription = "เลือกวันที่แจ้ง")
                            }
                        }
                    )
                }

                Column(modifier = Modifier.weight(1f)) {
                    Text(text = "วันที่ย้ายออก :", fontWeight = FontWeight.Bold)
                    OutlinedTextField(
                        value = moveOutDate,
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier.fillMaxWidth(),
                        trailingIcon = {
                            IconButton(onClick = {
                                showDatePicker { selectedDate ->
                                    if (reportDate.isNotEmpty()) {
                                        val report = thaiFormatter.parse(reportDate)
                                        val moveOut = thaiFormatter.parse(selectedDate)

                                        reportDateDate = report ?: Date()
                                        moveOutDateDate = moveOut ?: Date()

                                        if (moveOut != null) {
                                            if (moveOut.after(report)) {
                                                moveOutDate = selectedDate
                                                errorText = ""
                                            } else {
                                                errorText = "วันที่ย้ายออกต้องมากกว่าวันที่แจ้ง"
                                            }
                                        }
                                    } else {
                                        moveOutDate = selectedDate
                                        errorText = ""
                                    }
                                }
                            }) {
                                Icon(imageVector = Icons.Default.DateRange, contentDescription = "เลือกวันที่ย้ายออก")
                            }
                        }
                    )
                }
            }

            if (errorText.isNotEmpty()) {
                Text(text = errorText, color = Color.Red, fontSize = 14.sp, modifier = Modifier.padding(start = 16.dp, top = 4.dp))
            }


            MoveOutReasonDropdown(selectedReason) { selectedReason = it }

            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "รายละเอียดเพิ่มเติม (เว้นว่างได้):", fontWeight = FontWeight.Bold)

                OutlinedTextField(
                    value = additionalDetails,
                    onValueChange = { additionalDetails = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text(text = "ระบุรายละเอียดเพิ่มเติม (ถ้ามี)") },
                    maxLines = 5
                )
            }

            Column(
                modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .padding(5.dp),
                horizontalAlignment = Alignment.Start
            ){
                Text(
                    text = "หมายเหตุ",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
            }

            Column(
                modifier = Modifier.padding(top = 16.dp, start = 16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ){
                Text(
                    text = "เมื่อแจ้งย้ายแล้วกรุณารอคำอนุมัติจากทางหอพักอีกครั้ง",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.LightGray
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    val mysqlFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

                    val reportDateSql = mysqlFormatter.format(reportDateDate)
                    val moveOutDateSql = mysqlFormatter.format(moveOutDateDate)

                    insertLeavingUtility(
                        sharePreferences.member?.roomId!!,
                        reportDateSql,
                        moveOutDateSql,
                        selectedReason,
                        additionalDetails,
                        onResponse = {
                            Toast.makeText(context,"Insert leaving successfully",
                                Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                        },
                        onElse = { elseResponse->
                            Toast.makeText(context,"Insert leaving failed",
                                Toast.LENGTH_SHORT).show()
                            Log.e("Error",elseResponse.message())
                        },
                        onFailure = { t->
                            Toast.makeText(context,"Error onFailure",
                                Toast.LENGTH_SHORT).show()
                            Log.e("Error",t.message ?: "No Message")
                        }
                    )
                },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5E90FF),
                    contentColor = Color.White
                ),
                enabled = reportDate.isNotEmpty() && moveOutDate.isNotEmpty()
                        && selectedReason.isNotEmpty()
            ){
                Text(
                    text = "ส่ง",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoveOutReasonDropdown(selectedReason: String, onReasonSelected: (String) -> Unit) {
    val options = listOf(
        "หมดสัญญาเช่า",
        "ค่าเช่าแพงเกินไป",
        "ปัญหาด้านความปลอดภัย",
        "ต้องการย้ายไปที่ใหม่",
        "สภาพแวดล้อมไม่ดี",
        "ปัญหากับเจ้าของที่พัก",
        "หอพักไม่สะอาด / บริการไม่ดี",
        "เดินทางไปทำงาน/เรียนไม่สะดวก",
        "ต้องการห้องที่ใหญ่ขึ้น/เล็กลง",
        "เหตุผลส่วนตัวอื่น ๆ"
    )

    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "สาเหตุที่ย้ายออก :", fontWeight = FontWeight.Bold)

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = it }
        ) {
            OutlinedTextField(
                value = selectedReason,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                trailingIcon = {
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "เลือกเหตุผล")
                    }
                }
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { reason ->
                    DropdownMenuItem(
                        text = { Text(reason) },
                        onClick = {
                            onReasonSelected(reason)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

