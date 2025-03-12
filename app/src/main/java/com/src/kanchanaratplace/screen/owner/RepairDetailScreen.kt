package com.src.kanchanaratplace.screen.owner

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.ColorRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.src.kanchanaratplace.R
import com.src.kanchanaratplace.component.SampleScaffold
import com.src.kanchanaratplace.data.DefaultRooms
import com.src.kanchanaratplace.data.Repair
import com.src.kanchanaratplace.data.RepairImages
import com.src.kanchanaratplace.data.Status
import com.src.kanchanaratplace.status.OtherStatus
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.util.UUID
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Dialog
import com.src.kanchanaratplace.api_util.getRepairDetailImageUtility
import com.src.kanchanaratplace.api_util.getRepairDetailUtility
import com.src.kanchanaratplace.api_util.insertRepairDetailImageUtility
import com.src.kanchanaratplace.api_util.insertRepairDetailUtility
import com.src.kanchanaratplace.api_util.updateRepairCostStatusUtility
import com.src.kanchanaratplace.data.RepairDetail
import com.src.kanchanaratplace.data.RepairDetailImage
import com.src.kanchanaratplace.navigation.Screen


@Composable
fun RepairDetailScaffold(navController: NavHostController){
    SampleScaffold(navController,"รายละเอียด"){
        RepairDetailScreen(navController)
    }
}

@Composable
fun RepairDetailScreen(navController : NavHostController){
    val repairData = navController.previousBackStackEntry?.savedStateHandle?.get<Repair>("repair_data")
    val imageList = navController.previousBackStackEntry?.savedStateHandle?.get<List<RepairImages>>("images")
    val roomData = navController.previousBackStackEntry?.savedStateHandle?.get<DefaultRooms?>("room_data")

    var selectedImageUris by remember { mutableStateOf<List<Uri>>(emptyList()) }

    val context = LocalContext.current

    var detail by remember { mutableStateOf("") }
    var costDetail by remember { mutableStateOf("") }
    var cost by remember { mutableIntStateOf(0) }
    var validExpenses by remember { mutableStateOf<List<ExpenseItem>>(emptyList()) }
    var isValidExpenses by remember { mutableStateOf(false) }

    var infoDialog by remember { mutableStateOf(false) }

    fun Context.getImagePart(uri: Uri, name: String): MultipartBody.Part {
        val stream = contentResolver.openInputStream(uri)
        val mimeType = contentResolver.getType(uri) ?: "image/jpeg"
        val request = stream?.let {
            RequestBody.create(mimeType.toMediaTypeOrNull(), it.readBytes())
        }

        val extension = when (mimeType) {
            "image/jpeg", "image/jpg" -> ".jpg"
            "image/png" -> ".png"
            else -> ".jpg"
        }

        val uuid = UUID.randomUUID().toString()
        val filename = "image_${uuid}${extension}"

        return MultipartBody.Part.createFormData(
            name,
            filename,
            request!!
        )
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            selectedImageUris = selectedImageUris + it
        }
    }

    var repairDetail by remember { mutableStateOf<RepairDetail?>(null) }
    var repairDetailImageList by remember { mutableStateOf<List<RepairDetailImage>>(
        emptyList()
    ) }

    LaunchedEffect(repairData?.statusId ?: 0){
        when(repairData?.statusId){
            OtherStatus.SUCCESS.id -> {
                getRepairDetailUtility(
                    repairData.repairId,
                    onResponse = { response->
                        repairDetail = response
                    },
                    onElse = { elseResponse->
                        Toast.makeText(context,"Cannot fetch repair data",Toast.LENGTH_SHORT)
                            .show()
                        Log.e("Error",elseResponse.message())
                    },
                    onFailure = { t->
                        Toast.makeText(context,"Error onFailure",Toast.LENGTH_SHORT)
                            .show()
                        Log.e("Error",t.message ?: "No message")
                    }
                )

                getRepairDetailImageUtility(
                    repairData.repairId,
                    onResponse = { response->
                        repairDetailImageList = response
                    },
                    onElse = { elseResponse->
                        Toast.makeText(context,"Cannot fetch repair images",Toast.LENGTH_SHORT)
                            .show()
                        Log.e("Error",elseResponse.message())
                    },
                    onFailure = { t->
                        Toast.makeText(context,"Error onFailure",Toast.LENGTH_SHORT)
                            .show()
                        Log.e("Error",t.message ?: "No message")
                    }
                )
            }
            else -> {}
        }
    }

    val scrollState = rememberScrollState()
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "ห้อง ${roomData?.roomCode ?: ""}",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )

        LazyRow (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ){
            items(imageList ?: emptyList()){ image->
                Image(
                    painter = rememberAsyncImagePainter(
                        model = image.imagePath.replace(
                            "../uploads/","http://10.0.2.2:3000/uploads/"
                        )
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.width(125.dp).height(150.dp)
                )
            }
        }

        Column (
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.Start
        ){
            Text(
                text = "แจ้งเมื่อ: ${repairData?.createdAt}",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color(112,110,110)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = repairData?.repairTitle ?: "",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            if(repairData?.repairDetail?.isNotEmpty() == true){
                Text(
                    text = repairData.repairDetail,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }else{
                Text(
                    text = "ไม่มีรายละเอียดเพิ่มเติม",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }


        if(repairData?.statusId == OtherStatus.APPROVED.id){
            HorizontalDivider(
                color = Color.LightGray,
                modifier = Modifier.padding(start = 20.dp , end = 20.dp , top = 20.dp , bottom = 5.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "รายละเอียดการซ่อม",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 20.dp)
                    .align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(5.dp))

            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 100.dp),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.height(100.dp)
            ) {
                items(selectedImageUris.size) { index ->
                    val uri = selectedImageUris[index]
                    AsyncImage(
                        model = uri,
                        contentDescription = "Selected image",
                        modifier = Modifier.size(100.dp),
                        contentScale = ContentScale.Crop
                    )
                }

                item {
                    TextButton(
                        onClick = {
                            launcher.launch("image/*")
                        },
                        modifier = Modifier
                            .size(100.dp),
                        contentPadding = PaddingValues(15.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Gray,
                            contentColor = Color.White
                        ),
                        shape = RectangleShape
                    ) {
                        Text(
                            text = "+",
                            fontSize = 50.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = detail,
                onValueChange = {newValue->
                    detail = newValue
                },
                label = { Text("รายละเอียด") },
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.LightGray,
                    unfocusedIndicatorColor = Color.LightGray,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                ),
                modifier = Modifier.height(104.dp).fillMaxWidth()
                    .padding(horizontal = 20.dp)
            )

            Row (
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 20.dp , start = 20.dp , end = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                ){
                    Text(
                        text = "ค่าใช้จ่าย",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                    )

                    IconButton(
                        onClick = {
                            infoDialog = true
                        },
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = Color.White,
                            contentColor = Color.LightGray
                        )
                    ){
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

                Text(
                    text = "รวม: $cost บาท",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            isValidExpenses = ExpenseTextField(
                value = costDetail,
                onValueChange = { costDetail = it },
                onExpensesParsed = { items ->
                    validExpenses = items
                    cost = items.sumOf { it.price }
                }
            )

        }else if(repairData?.statusId == OtherStatus.SUCCESS.id){

            HorizontalDivider(
                color = Color.LightGray,
                modifier = Modifier.padding(start = 20.dp , end = 20.dp , top = 20.dp , bottom = 5.dp)
            )
            if (repairData.statusId == OtherStatus.SUCCESS.id){
                Text(
                    text = "ซ่อมเมื่อ ${repairData.updatedAt}",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(112,110,110),
                    modifier = Modifier.padding(start = 20.dp)
                        .align(Alignment.Start)
                )
            }

            Text(
                text = "รายละเอียดการซ่อม",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 20.dp)
                    .align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(5.dp))

            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 100.dp),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.height(100.dp)
            ){
                items(repairDetailImageList){ image->
                    Image(
                        painter = rememberAsyncImagePainter(
                            model = image.repairDetailImagePath.replace(
                                "../uploads/","http://10.0.2.2:3000/uploads/"
                            )
                        ),
                        contentDescription = "Selected image",
                        modifier = Modifier.size(100.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = repairDetail?.repairDetailDetail ?: "",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(start = 20.dp)
                    .align(Alignment.Start)
            )

            Row (
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 20.dp , start = 20.dp , end = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                ){
                    Text(
                        text = "ค่าใช้จ่าย",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                    )

                }

                Text(
                    text = "รวม: ${repairData.repairCost} บาท",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Text(
                text = repairDetail?.repairDetailCostDetail ?: "Hello",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(start = 20.dp)
                    .align(Alignment.Start)
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        if(repairData?.statusId == OtherStatus.PENDING.id){
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ){
                Button(
                    onClick = {

                    },
                    modifier = Modifier.width(163.dp).height(40.dp),
                    shape = CircleShape,
                    border = BorderStroke(
                        width = 1.dp,
                        color = Color(94,144,255)
                    ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    )
                ){
                    Text(
                        text = "ย้อนกลับ",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(94,144,255)
                    )
                }
                Button(
                    onClick = {

                    },
                    modifier = Modifier.width(163.dp).height(40.dp),
                    shape = CircleShape,
                    border = BorderStroke(
                        width = 1.dp,
                        color = Color.Transparent
                    ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(94,144,255)
                    )
                ){
                    Text(
                        text = "รับเรื่อง",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

            }
        }
        else if(repairData?.statusId == OtherStatus.APPROVED.id){
            TextButton(
                onClick = {
                    updateRepairCostStatusUtility(
                        repairData.repairId,
                        cost,
                        OtherStatus.SUCCESS.id,
                        onResponse = {
                            insertRepairDetailUtility(
                                repairData.repairId,
                                detail,
                                costDetail,
                                onResponse = {
                                    Toast.makeText(context,"Insert repair detail successfully",
                                        Toast.LENGTH_SHORT).show()

                                    selectedImageUris.forEach { uri->
                                        val path = context.getImagePart(uri,"repair_detail_image_path")
                                        insertRepairDetailImageUtility(
                                            repairData.repairId,
                                            path,
                                            onResponse = {
                                                Toast.makeText(context,"Insert repair detail image successfully",
                                                    Toast.LENGTH_SHORT).show()

                                                if(uri == selectedImageUris[selectedImageUris.size - 1]){
                                                    navController.navigate(Screen.RepairSuccess.route)
                                                }
                                            },
                                            onElse = { elseResponse->
                                                Toast.makeText(context,"Insert repair detail image failed",
                                                    Toast.LENGTH_SHORT).show()
                                                Log.e("Error",elseResponse.message())
                                            },
                                            onFailure = { t->
                                                Toast.makeText(context,"Error onFailure",
                                                    Toast.LENGTH_SHORT).show()
                                                Log.e("Error",t.message ?: "No message")
                                            }
                                        )
                                    }
                                },
                                onElse = { elseResponse->
                                    Toast.makeText(context,"Insert repair detail failed",
                                        Toast.LENGTH_SHORT).show()
                                    Log.e("Error",elseResponse.message())
                                },
                                onFailure = { t->
                                    Toast.makeText(context,"Error onFailure",
                                        Toast.LENGTH_SHORT).show()
                                    Log.e("Error",t.message ?: "No message")
                                }
                            )
                        },
                        onElse = {elseResponse->
                            Toast.makeText(context,"Update failed",
                                Toast.LENGTH_SHORT).show()
                            Log.e("Error",elseResponse.message())
                        },
                        onFailure = { t->
                            Toast.makeText(context,"Error onFailure",
                                Toast.LENGTH_SHORT).show()
                            Log.e("Error",t.message ?: "No message")
                        }
                    )
                },
                modifier = Modifier.fillMaxWidth()
                    .padding(20.dp)
                    .height(45.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = Color(0xFF5E90FF)
                ),
                enabled = isValidExpenses
            ){
                Text(
                    text = "ซ่อมแล้ว",
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }


    if(infoDialog){
        Dialog(onDismissRequest = { infoDialog = false }) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        IconButton(
                            onClick = { infoDialog = false },
                            modifier = Modifier.align(Alignment.TopEnd)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close",
                                tint = Color.Black
                            )
                        }
                    }

                    Image(
                        painter = painterResource(id = R.drawable.calendar_planner),
                        contentDescription = "Calendar",
                        modifier = Modifier
                            .size(160.dp)
                            .padding(vertical = 16.dp)
                    )

                    Text(
                        text = "กรอกรายการค่าใช้จ่าย และราคา",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 8.dp)
                    )

                    Text(
                        text = "[ตัวอย่าง : ค่าจ้าง 100]",
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 4.dp)
                    )

                    Text(
                        text = "กรุณาใส่ , (Comma) คั่นรายการ",
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 8.dp)
                    )

                    Text(
                        text = "กรณีมีรายการค่าใช้จ่ายมากกว่า 1 รายการ",
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
                    )
                }
            }
        }
    }
}

data class ExpenseItem(val name: String, val price: Int)

fun parseExpenses(input: String): Pair<List<ExpenseItem>, List<String>> {
    if (input.isBlank()) {
        return Pair(emptyList(), listOf("ข้อความว่างเปล่า กรุณาระบุรายการค่าใช้จ่าย"))
    }

    val items = input.split(",")

    val validItems = mutableListOf<ExpenseItem>()
    val errorMessages = mutableListOf<String>()

    val expensePattern = """^([^0-9]+)\s+(\d+)$""".toRegex()

    items.forEachIndexed { index, item ->
        val trimmedItem = item.trim()

        if (trimmedItem.isEmpty()) {
            errorMessages.add("รายการที่ ${index + 1} ว่างเปล่า")
            return@forEachIndexed
        }

        val matchResult = expensePattern.matchEntire(trimmedItem)

        if (matchResult != null) {
            val name = matchResult.groupValues[1].trim()
            val priceStr = matchResult.groupValues[2]

            try {
                val price = priceStr.toInt()
                if (price <= 0) {
                    errorMessages.add("รายการที่ ${index + 1} '$trimmedItem': ราคาต้องมากกว่า 0")
                } else {
                    validItems.add(ExpenseItem(name, price))
                }
            } catch (e: NumberFormatException) {
                errorMessages.add("รายการที่ ${index + 1} '$trimmedItem': ราคาไม่ถูกต้อง")
            }
        } else {
            if (!trimmedItem.contains(" ")) {
                errorMessages.add("รายการที่ ${index + 1} '$trimmedItem': ไม่มีช่องว่างระหว่างชื่อรายการและราคา")
            } else if (trimmedItem.endsWith(" ")) {
                errorMessages.add("รายการที่ ${index + 1} '$trimmedItem': ไม่มีการระบุราคา")
            } else {
                val hasDigit = trimmedItem.any { it.isDigit() }
                if (!hasDigit) {
                    errorMessages.add("รายการที่ ${index + 1} '$trimmedItem': ไม่มีการระบุราคา")
                } else {
                    errorMessages.add("รายการที่ ${index + 1} '$trimmedItem': รูปแบบไม่ถูกต้อง (ต้องเป็น 'ชื่อรายการ ราคา')")
                }
            }
        }
    }

    return Pair(validItems, errorMessages)
}

@SuppressLint("ComposableNaming")
@Composable
fun ExpenseTextField(
    value: String,
    onValueChange: (String) -> Unit,
    onExpensesParsed: (List<ExpenseItem>) -> Unit
) : Boolean {
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column {
        OutlinedTextField(
            value = value,
            onValueChange = { newValue ->
                onValueChange(newValue)

                if (newValue.isNotEmpty()) {
                    val (validItems, errors) = parseExpenses(newValue)

                    if (errors.isNotEmpty()) {
                        errorMessage = "รูปแบบไม่ถูกต้อง: ${errors.first()}"
                    } else {
                        errorMessage = null
                        onExpensesParsed(validItems)
                    }
                } else {
                    errorMessage = null
                }
            },
            label = { Text("ค่าใช้จ่าย") },
            placeholder = { Text("สายฉีดชำระ 70, ค่าช่าง 60") },
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.LightGray,
                unfocusedIndicatorColor = Color.LightGray,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                errorIndicatorColor = Color.Red,
                errorContainerColor = Color.White
            ),
            isError = errorMessage != null,
            supportingText = {
                errorMessage?.let {
                    Text(
                        text = it,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            },
            modifier = Modifier
                .height(if (errorMessage != null) 124.dp else 104.dp)
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )
    }

    return errorMessage == null
}

