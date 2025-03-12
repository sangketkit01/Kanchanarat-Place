package com.src.kanchanaratplace.screen.main

import android.app.DatePickerDialog
import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.src.kanchanaratplace.R
import com.src.kanchanaratplace.api_util.updateProfileUtility
import com.src.kanchanaratplace.component.SampleScaffold
import com.src.kanchanaratplace.session.MemberSharePreferencesManager
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.Calendar
import java.util.UUID

@Composable
fun ProfileDetailScaffold(navController: NavHostController){
    SampleScaffold(navController,"แก้ไขโปรไฟล์"){
        ProfileDetailScreen(navController)
    }
}

@Composable
fun ProfileDetailScreen(navController : NavHostController){
    val scrollState = rememberScrollState()

    val context = LocalContext.current
    val sharePreferences = remember { MemberSharePreferencesManager(context) }

    var name by remember { mutableStateOf(sharePreferences.member?.name ?: "") }
    var email by remember { mutableStateOf(sharePreferences.member?.email ?: "") }
    var phone by remember { mutableStateOf(sharePreferences.member?.phone ?: "") }

    var birth by remember { mutableStateOf(sharePreferences.member?.birthDate ?: Date()) }

    fun formatThaiDate(date: Date?): String {
        if (date == null) return ""

        val thaiLocale = Locale("th", "TH")
        val calendar = Calendar.getInstance()
        calendar.time = date

        val thaiYear = calendar.get(Calendar.YEAR) + 543

        val dayFormat = SimpleDateFormat("d", thaiLocale)
        val monthFormat = SimpleDateFormat("MMMM", thaiLocale)

        val day = dayFormat.format(date)
        val month = monthFormat.format(date)

        return "$day $month $thaiYear"
    }

    var thaiDateString by remember { mutableStateOf(formatThaiDate(birth)) }

    val calendar = Calendar.getInstance()
    calendar.time = birth

    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = DatePickerDialog(
        context,
        { _, selectedYear, selectedMonth, selectedDay ->
            calendar.set(selectedYear, selectedMonth, selectedDay)
            val date = calendar.time

            birth = date
            thaiDateString = formatThaiDate(date)
        },
        year,
        month,
        day
    )

    fun parseThaiDateToMysqlDate(thaiDateString: String): String {
        try {
            val parts = thaiDateString.split(" ")
            if (parts.size != 3) return ""

            val day = parts[0].toIntOrNull() ?: return ""
            val thaiMonth = parts[1]
            val thaiYear = parts[2].toIntOrNull() ?: return ""

            val year = thaiYear - 543

            val thaiMonths = listOf(
                "มกราคม", "กุมภาพันธ์", "มีนาคม", "เมษายน", "พฤษภาคม", "มิถุนายน",
                "กรกฎาคม", "สิงหาคม", "กันยายน", "ตุลาคม", "พฤศจิกายน", "ธันวาคม"
            )

            val month = thaiMonths.indexOf(thaiMonth) + 1
            if (month == 0) return ""

            return String.format("%04d-%02d-%02d", year, month, day)

        } catch (e: Exception) {
            return ""
        }
    }

    val mysqlDate = parseThaiDateToMysqlDate(thaiDateString)

    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    fun Context.getImagePart(uri: Uri, name : String): MultipartBody.Part {
        val stream = contentResolver.openInputStream(uri)
        val mimeType = contentResolver.getType(uri) ?: "image/jpeg"
        val request = stream?.let {
            RequestBody.create(mimeType.toMediaTypeOrNull(), it.readBytes())
        }

        val extension = when(mimeType) {
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
            selectedImageUri = it
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.height(10.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
                .height(230.dp)
                .padding(20.dp),
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ){
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                if(selectedImageUri != null){
                    Image(
                        painter = rememberAsyncImagePainter(
                            model = selectedImageUri
                        ),
                        contentScale = ContentScale.Crop,
                        contentDescription = null,
                        modifier = Modifier.size(120.dp)
                            .clickable {
                                launcher.launch("image/*")
                            }.clip(CircleShape)
                    )
                }else{
                    if(sharePreferences.member?.imagePath != null){
                        Image(
                            painter = rememberAsyncImagePainter(
                                model = sharePreferences.member?.imagePath
                                    ?.replace("../uploads/","http://10.0.2.2:3000/uploads/")
                            ),
                            contentScale = ContentScale.Crop,
                            contentDescription = null,
                            modifier = Modifier.size(120.dp)
                                .clickable {
                                    launcher.launch("image/*")
                                }.clip(CircleShape)
                        )
                    }else{
                        Image(
                            painter = painterResource(R.drawable.example_profile),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(120.dp)
                                .clickable {
                                    launcher.launch("image/*")
                                }.clip(CircleShape)
                        )
                    }
                }
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "ชื่อ-สกุล",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .padding(start = 15.dp)
                    .align(Alignment.Start)
            )

            OutlinedTextField(
                value = name,
                onValueChange = {newValue->
                    name = newValue
                },
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.LightGray,
                    unfocusedIndicatorColor = Color.LightGray,
                ),
                modifier = Modifier.fillMaxWidth()
                    .padding(15.dp)
            )

            Text(
                text = "อีเมลล์",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .padding(start = 15.dp)
                    .align(Alignment.Start)
            )

            OutlinedTextField(
                value = email,
                onValueChange = {newValue->
                    email = newValue
                },
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.LightGray,
                    unfocusedIndicatorColor = Color.LightGray,
                ),
                modifier = Modifier.fillMaxWidth()
                    .padding(15.dp)
            )

            Text(
                text = "เบอร์โทรศัพท์",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .padding(start = 15.dp)
                    .align(Alignment.Start)
            )

            OutlinedTextField(
                value = phone,
                onValueChange = {newValue->
                    phone = newValue
                },
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.LightGray,
                    unfocusedIndicatorColor = Color.LightGray,
                ),
                modifier = Modifier.fillMaxWidth()
                    .padding(15.dp)
            )

            Text(
                text = "วันเกิด",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .padding(start = 15.dp)
                    .align(Alignment.Start)
            )

            OutlinedTextField(
                value = thaiDateString,
                onValueChange = { /* ไม่อนุญาตให้แก้ไขด้วยการพิมพ์ */ },
                readOnly = true,
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.LightGray,
                    unfocusedIndicatorColor = Color.LightGray,
                ),
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_menu_my_calendar),
                        contentDescription = "เลือกวันที่",
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            datePickerDialog.show()
                        }
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        datePickerDialog.show()
                    }
            )

            Button(
                onClick = {
                    updateProfileUtility(
                        sharePreferences.member?.memberId ?: 0,
                        name,
                        email,
                        phone,
                        mysqlDate,
                        selectedImageUri?.let { context.getImagePart(it,"image_path") },
                        onResponse = {
                            Toast.makeText(context,"Update profile successfully",Toast.LENGTH_SHORT)
                                .show()

                            Log.e("Data","$name , $email")
                            navController.popBackStack()
                        },
                        onElse = { elseResponse->
                            Toast.makeText(context,"Update profile failed",Toast.LENGTH_SHORT)
                                .show()
                            Log.e("Error",elseResponse.message())
                        },
                        onFailure = { t->
                            Toast.makeText(context,"Error onFailure",Toast.LENGTH_SHORT)
                                .show()
                            Log.e("Error",t.message ?: "No Message")
                        }
                    )
                },
                shape = CircleShape,
                modifier = Modifier.padding(vertical = 15.dp)
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5E90FF),
                    contentColor = Color.White
                )
            ){
                Text(
                    text = "บันทึก",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}