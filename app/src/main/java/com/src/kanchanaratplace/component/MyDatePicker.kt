package com.src.kanchanaratplace.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Calendar

@Composable
fun MyDatePicker(
    selectedMonthIndex: Int,
    selectedYearCE: Int,
    onMonthSelected: (Int) -> Unit,
    onYearSelected: (Int) -> Unit
) {
    val months = listOf(
        "มกราคม", "กุมภาพันธ์", "มีนาคม", "เมษายน", "พฤษภาคม", "มิถุนายน",
        "กรกฎาคม", "สิงหาคม", "กันยายน", "ตุลาคม", "พฤศจิกายน", "ธันวาคม"
    )
    val currentYear = Calendar.getInstance().get(Calendar.YEAR)
    val years = (currentYear - 10..currentYear + 10).toList()

    var expandedMonth by remember { mutableStateOf(false) }
    var expandedYear by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${months[selectedMonthIndex - 1]}/${selectedYearCE + 543}",
            modifier = Modifier.weight(1f),
            fontSize = 18.sp
        )

        Column {
            Box(modifier = Modifier.clickable { expandedMonth = true }) {
                Text(text = "เลือกเดือน ▼", modifier = Modifier.padding(8.dp))
                DropdownMenu(expanded = expandedMonth, onDismissRequest = { expandedMonth = false }) {
                    months.forEachIndexed { index, month ->
                        DropdownMenuItem(text = { Text(month) }, onClick = {
                            onMonthSelected(index + 1)
                            expandedMonth = false
                        })
                    }
                }
            }
            Box(modifier = Modifier.clickable { expandedYear = true }) {
                Text(text = "เลือกปี ▼", modifier = Modifier.padding(8.dp))
                DropdownMenu(expanded = expandedYear, onDismissRequest = { expandedYear = false }) {
                    years.forEach { year ->
                        DropdownMenuItem(text = { Text((year + 543).toString()) }, onClick = {
                            onYearSelected(year)
                            expandedYear = false
                        })
                    }
                }
            }
        }
    }
}
