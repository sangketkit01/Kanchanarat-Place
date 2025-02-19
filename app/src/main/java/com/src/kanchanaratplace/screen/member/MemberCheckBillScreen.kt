package com.src.kanchanaratplace.screen.member

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import com.src.kanchanaratplace.R
import com.src.kanchanaratplace.api_util.getOneRoomUtility
import com.src.kanchanaratplace.api_util.payBillUtility
import com.src.kanchanaratplace.component.SampleScaffold
import com.src.kanchanaratplace.data.Bill
import com.src.kanchanaratplace.data.DefaultRooms
import com.src.kanchanaratplace.navigation.Screen
import com.src.kanchanaratplace.session.MemberSharePreferencesManager
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.util.UUID

@Composable
fun MemberCheckBIllScaffold(navController: NavHostController){
    SampleScaffold(navController,"ชำระเงิน") {
        MemberCheckBillScreen(navController)
    }
}

@Composable
fun MemberCheckBillScreen(navController : NavHostController){
    val context = LocalContext.current
    val sharePreferences = remember { MemberSharePreferencesManager(context) }

    var roomData by remember { mutableStateOf<DefaultRooms?>(null) }
    var billData = navController.previousBackStackEntry?.savedStateHandle?.get<Bill>("bill_data")

    val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current
    val lifecycleState by lifecycleOwner.lifecycle.currentStateFlow.collectAsState()
    LaunchedEffect (lifecycleState){
        when(lifecycleState){
            Lifecycle.State.DESTROYED -> {}
            Lifecycle.State.INITIALIZED -> {}
            Lifecycle.State.CREATED -> {}
            Lifecycle.State.STARTED -> {}
            Lifecycle.State.RESUMED -> {
                sharePreferences.member?.roomId?.let {
                    getOneRoomUtility(
                        roomId = it,
                        onResponse = { response->
                            roomData = response
                        },
                        onElse = {
                            Toast.makeText(context,"Data not found", Toast.LENGTH_SHORT).show()
                        },
                        onFailure = { t->
                            Toast.makeText(context,"Error Check LogCat", Toast.LENGTH_SHORT).show()
                            t.message?.let { Log.e("Error",it) }
                        }
                    )
                }
            }
        }
    }

    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    fun Context.getImagePart(uri: Uri): MultipartBody.Part {
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
            "slip_part",
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

    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(
                color = Color.White
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        Spacer(modifier = Modifier.height(10.dp))

        Column (
            modifier = Modifier.fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "ค่าเช่าเดือน มกราคม 2568 : " +
                        "${(billData?.totalPrice ?: 0).toString().format("%,d")} บาท",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clip(RoundedCornerShape(10.dp))
                    .border(
                        width = 1.dp,
                        color = Color(94, 144, 255, 255),
                        shape = RoundedCornerShape(10.dp)
                    ).padding(10.dp)
            )

            Card (
                modifier = Modifier.fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 30.dp),
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(
                    width = 1.dp,
                    color = Color.LightGray
                ),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ){
                Column(
                    modifier = Modifier.fillMaxSize()
                        .padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.real_qr),
                        contentScale = ContentScale.Fit,
                        contentDescription = null,
                        modifier = Modifier.width(236.dp).height(201.dp)
                    )

                    Text(
                        text = "กาญจนรัตน์ เพลส",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.ExtraBold,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Text(
                text = "หรือ",
                fontSize = 16.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(163, 163, 163, 255)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "ธ.กรุงไทย หมายเลขบัญชี 3300550555\n" +
                        "ชื่อบัญชี กาญจนรัตน์ เพลส",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(15.dp))

            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                OutlinedButton(
                    onClick = {
                        launcher.launch("image/*")
                    },
                    border = BorderStroke(1.dp, Color.Gray),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Gray),
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Text(
                        text = "แบบไฟล์",
                        fontSize = 18.sp,
                        color = Color.Gray
                    )
                }
            }

            if(selectedImageUri != null){
                Text(
                    text = "เลือกรูปภาพแล้ว"
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            Button(
                onClick = {
                    selectedImageUri?.let {
                        payBillUtility(
                            billData?.billId ?: 0,
                            context.getImagePart(it),
                            onResponse = {
                                Toast.makeText(context, "ส่งบิลสำเร็จ รอดำเนินการ",
                                    Toast.LENGTH_SHORT).show()

                                navController.navigate(Screen.MemberApartment.route)
                            },
                            onElse = { response->
                                Toast.makeText(context, "เกิดข้อผิดพลาด ส่งบิลไม่สำเร็จ",
                                    Toast.LENGTH_SHORT).show()
                                response.errorBody()?.toString()?.let { it1 -> Log.e("Error", it1) }
                            },
                            onFailure = { t->
                                Toast.makeText(context, "Error Check LogCat", Toast.LENGTH_SHORT).show()
                                t.message?.let {it2-> Log.e("Error", it2) }
                            }
                        )
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(94, 144, 255, 255),
                    contentColor = Color.White
                ),
                modifier = Modifier.width(226.dp).height(39.dp),
                enabled = selectedImageUri != null
            ) {
                Text(
                    text = "เสร็จสิ้น",
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}