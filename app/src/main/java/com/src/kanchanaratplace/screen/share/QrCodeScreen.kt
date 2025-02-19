package com.src.kanchanaratplace.screen.share

import android.content.Context
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import com.src.kanchanaratplace.R
import com.src.kanchanaratplace.api.RoomAPI
import com.src.kanchanaratplace.api_util.checkReservationUtility
import com.src.kanchanaratplace.api_util.getReservationUtility
import com.src.kanchanaratplace.api_util.insertContractUtility
import com.src.kanchanaratplace.api_util.reservingRoomUtility
import com.src.kanchanaratplace.component.BlueWhiteButton
import com.src.kanchanaratplace.component.SampleScaffold
import com.src.kanchanaratplace.component.WhiteBlueButton
import com.src.kanchanaratplace.data.Contract
import com.src.kanchanaratplace.data.Reservation
import com.src.kanchanaratplace.navigation.Screen
import com.src.kanchanaratplace.status.ContractEnum
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import okhttp3.internal.wait
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun QrCodeScaffold(navController: NavHostController){
    SampleScaffold(navController,"ชำระค่าบริการ") {
        QRCodeScreen(navController)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun QRCodeScreen(navController : NavHostController){
    val before = navController.previousBackStackEntry?.savedStateHandle?.get<String>("before")
    val next = navController.previousBackStackEntry?.savedStateHandle?.get<String>("next")
    val previousRoute = navController.previousBackStackEntry?.savedStateHandle?.get<String>("previous_route")
    val reservation = navController.previousBackStackEntry?.savedStateHandle?.get<Reservation>("reservation")


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

    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            selectedImageUri = it
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
        Card(
            modifier = Modifier.fillMaxWidth()
                .padding(20.dp),
            shape = RoundedCornerShape(20.dp),
            border = BorderStroke(
                width = 1.dp,
                color = Color.LightGray
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Column (
                modifier = Modifier.fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = "ช่องทางการชำระเงิน",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.align(Alignment.Start)
                )

                Column (
                    modifier = Modifier.fillMaxWidth()
                        .padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(
                        text = "คิวอาร์โค้ด",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.align(Alignment.Start)
                            .padding(vertical = 10.dp)
                    )

                    Image(
                        painter = painterResource(id = R.drawable.qrcode),
                        contentDescription = null,
                        modifier = Modifier.width(150.dp).height(150.dp)
                            .padding(vertical = 10.dp),
                        contentScale = ContentScale.Fit
                    )

                    Text(
                        text = "หรือ",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.align(Alignment.Start)
                            .padding(top = 10.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Text(
                            text = "เลขบัญชี",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                        )

                        Text(
                            text = "123-4-56789-2\n" +
                                    "ธนาคารกสิกรไทย\n" +
                                    "นางมานี มีนา",
                            fontSize = 18.sp,
                            color = Color(30, 29, 29, 255)
                        )
                    }
                }
            }
        }

        Row (
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 25.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.Start
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
            Text("เลือกรูปภาพแล้ว",
                modifier = Modifier.padding(horizontal = 25.dp))
        }

        Column (
            modifier = Modifier.fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            WhiteBlueButton(
                text = "กลับไปหน้าก่อนหน้านี้",
                onClick = {
                    if (before != null) {
                        navController.navigate(before)
                    }
                }
            )


            Spacer(modifier = Modifier.height(10.dp))

            BlueWhiteButton(
                text = "ชำระเงิน",
                onClick = {
                    if (next != null && previousRoute == Screen.PayReservation.route) {
                        selectedImageUri?.let { uri ->
                            val imagePart = context.getImagePart(uri)
                            reservingRoomUtility(
                                reservation?.roomId ?: 0,
                                reservation?.statusId ?: 0,
                                reservation?.name ?: "",
                                reservation?.phone ?: "",
                                reservation?.email ?: "",
                                reservation?.line ?: "",
                                imagePart,
                                onResponse = {
                                    checkReservationUtility(
                                        reservation?.name ?: "",
                                        reservation?.phone ?: "",
                                        onResponse = { response ->
                                            val reservationId = response.reservationId ?: 0
                                            Toast.makeText(context, "Reserve Successfully", Toast.LENGTH_SHORT).show()

                                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                                "reservation_id", reservationId
                                            )
                                            navController.navigate(next)
                                        },
                                        onElse = {
                                            Toast.makeText(context, "Reservation not found", Toast.LENGTH_SHORT).show()
                                        },
                                        onFailure = { t ->
                                            Toast.makeText(context, "Error Check LogCat", Toast.LENGTH_SHORT).show()
                                            t.message?.let { Log.e("Error", it) }
                                        }
                                    )
                                },
                                onElse = { els ->
                                    Toast.makeText(context, "Reserve Failed + ${reservation?.roomId}", Toast.LENGTH_SHORT).show()
                                },
                                onFailure = { t ->
                                    Toast.makeText(context, "Error Check LogCat", Toast.LENGTH_SHORT).show()
                                    t.message?.let { Log.e("Error", it) }
                                }
                            )
                        }
                    }


                    if(next != null && previousRoute == Screen.ContractFeeDetail.route){
                        val reservedData = navController.previousBackStackEntry?.savedStateHandle?.get<Reservation>("reservation_data")
                        selectedImageUri?.let { uri ->
                            val imagePart = context.getImagePart(uri)
                            insertContractUtility(
                                reservedData?.roomId ?: 0,
                                reservedData?.reservationId ?: 0,
                                "Hello World",
                                ContractEnum.StartContract.length,
                                imagePart,
                                onResponse = {
                                    Toast.makeText(context,"ทำสัญญาสำเร็จ รอการอนุมัติ",Toast.LENGTH_SHORT).show()
                                    navController.navigate(next)
                                },
                                onElse = {els->
                                    val errorBody = els.errorBody()?.string() ?: "Unknown error"
                                    Log.e("API Error", "Response Code: ${els.code()}, Message: $errorBody")

                                    Toast.makeText(context, "เกิดข้อผิดพลาด: $errorBody", Toast.LENGTH_LONG).show()
                                },
                                onFailure = {t->
                                    Toast.makeText(context,"Error Check LogCat",Toast.LENGTH_SHORT).show()
                                    t.message?.let { Log.e("Error",it) }
                                }
                            )
                        }
                    }

                }
            )
        }

        Spacer(modifier = Modifier.height(40.dp))
    }

}

