package com.src.kanchanaratplace.screen.share

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import com.src.kanchanaratplace.component.BaseScaffold
import com.src.kanchanaratplace.component.BlueWhiteButton
import com.src.kanchanaratplace.component.SampleScaffold
import com.src.kanchanaratplace.component.WhiteBlueButton
import com.src.kanchanaratplace.data.Reservation
import com.src.kanchanaratplace.navigation.Screen
import com.src.kanchanaratplace.screen.reservation.PayReservationScreen
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.UUID

@Composable
fun QrCodeScaffold(navController: NavHostController){
    SampleScaffold(navController,"ชำระค่าบริการ") {
        QRCodeScreen(navController)
    }
}

@Composable
fun QRCodeScreen(navController : NavHostController){
    val before = navController.previousBackStackEntry?.savedStateHandle?.get<String>("before")
    val next = navController.previousBackStackEntry?.savedStateHandle?.get<String>("next")
    val previousRoute = navController.previousBackStackEntry?.savedStateHandle?.get<String>("previous_route")
    val reservation = navController.previousBackStackEntry?.savedStateHandle?.get<Reservation>("reservation")
    val room = navController.previousBackStackEntry?.savedStateHandle?.get<String>("room")

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

    val roomClient = RoomAPI.create()
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
                            val statusIdBody = reservation?.statusId.toString()
                                .toRequestBody("text/plain".toMediaTypeOrNull())
                            val nameBody =
                                (reservation?.name ?: "").toRequestBody("text/plain".toMediaTypeOrNull())
                            val phoneBody =
                                (reservation?.phone ?: "").toRequestBody("text/plain".toMediaTypeOrNull())
                            val emailBody =
                                (reservation?.email ?: "").toRequestBody("text/plain".toMediaTypeOrNull())
                            val lineBody =
                                (reservation?.line ?: "").toRequestBody("text/plain".toMediaTypeOrNull())

                            roomClient.reservingRoom(
                                reservation?.roomId ?: 0,
                                statusIdBody,
                                nameBody,
                                phoneBody,
                                emailBody,
                                lineBody,
                                imagePart
                            ).enqueue(object : Callback<Reservation> {
                                override fun onResponse(call: Call<Reservation>, response: Response<Reservation>) {
                                    if (response.isSuccessful) {
                                        val reservationId = response.body()?.reservationId ?: 0

                                        Toast.makeText(context,"Reserve Successfully $reservationId",Toast.LENGTH_SHORT).show()
                                        navController.currentBackStackEntry?.savedStateHandle?.set(
                                            "reservation_id" , reservationId
                                        )
                                        navController.navigate(next)
                                    }else{
                                        Toast.makeText(context,"Reserve Failed + ${reservation?.roomId}",Toast.LENGTH_SHORT).show()
                                    }
                                }

                                override fun onFailure(call: Call<Reservation>, t: Throwable) {
                                    Toast.makeText(context,"Error onFailure + ${t.message}",Toast.LENGTH_SHORT).show()
                                }
                            })
                        }
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(40.dp))
    }

}


