package com.src.kanchanaratplace.screen.share

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.src.kanchanaratplace.R
import com.src.kanchanaratplace.component.SampleScaffold
import com.src.kanchanaratplace.data_util.Receipt
import retrofit2.http.Body
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun ReceiptScaffold(navController: NavHostController){
    SampleScaffold(navController,"ใบเสร็จ") {
        ReceiptScreen(navController)
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun ReceiptScreen(navController : NavHostController){
    val receiptData = navController.previousBackStackEntry?.savedStateHandle
        ?.get<List<Receipt>>("receipt_data") ?: emptyList()

    val totalPrice = navController.previousBackStackEntry?.savedStateHandle
        ?.get<Int>("total_price") ?: 0

    val name = navController.previousBackStackEntry?.savedStateHandle
        ?.get<String>("name") ?: ""

    val context = LocalContext.current
    val cardView = remember { ComposeView(context) }

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
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(236, 233, 233, 255))
                .padding(vertical = 10.dp, horizontal = 20.dp),
            horizontalArrangement = Arrangement.End
        ){
            Button(
                onClick = {
                    saveCardAsImage(cardView,context)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                contentPadding = PaddingValues(0.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.download1),
                    contentScale = ContentScale.Fit,
                    contentDescription = "Download contract",
                    modifier = Modifier.size(25.dp)
                )
            }
        }

        val cardContent = @Composable {
            Card(
                modifier = Modifier.fillMaxWidth()
                    .padding(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                border = BorderStroke(
                    width = 1.dp,
                    color = Color.LightGray
                )
            ){
                Column (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Spacer(modifier = Modifier.height(10.dp))

                    Image(
                        painter = painterResource(R.drawable.logo),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.width(116.dp).height(42.dp)
                    )

                    Text(
                        text = "หอกาญจนรัตน์ เพลส",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    HorizontalDivider(color = Color.LightGray , modifier = Modifier.padding(vertical = 10.dp))

                    Text(
                        text = "ใบเสร็จรับเงิน",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row (
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "เลขที่ใบเสร็จ",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                            )

                            Text(
                                text = "00001",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(128, 128, 128, 255),
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "วันที่",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold
                            )

                            Text(
                                text = "7/1/2567",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(128, 128, 128, 255)
                            )
                        }
                    }

                    Row (
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ){
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "ชื่อ",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                            )

                            Text(
                                text = name,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(128, 128, 128, 255),
                            )
                        }
                    }


                    Row (
                        modifier = Modifier.fillMaxWidth()
                            .padding(vertical = 15.dp)
                            .background(Color(217, 217, 217, 255))
                            .padding(vertical = 5.dp, horizontal = 15.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            text = "รายการ",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        Text(
                            text = "จำนวนเงิน(บาท)",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Column (
                        modifier = Modifier.fillMaxWidth()
                            .padding(horizontal = 15.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ){
                        receiptData.forEach { receipt->
                            Row (
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ){
                                Text(
                                    text = receipt.name,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color(128, 128, 128, 255),
                                    textAlign = TextAlign.Center
                                )

                                Text(
                                    text = String.format("%,d", receipt.price),
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 15.sp,
                                    color = Color(128, 128, 128, 255),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }

                    HorizontalDivider(modifier = Modifier.padding(top = 10.dp), color = Color.LightGray)

                    Row (
                        modifier = Modifier.fillMaxWidth()
                            .padding(15.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Text(
                            text = "รวมทั้งหมด",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                        )

                        Text(
                            text = String.format("%,d", totalPrice),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            color = Color(128, 128, 128, 255)
                        )
                    }

                    HorizontalDivider(color = Color.LightGray)

                    Row (
                        modifier = Modifier.fillMaxWidth()
                            .padding(15.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){

                        Text(
                            text = "ชำระแล้ว",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                        )

                        Text(
                            text = String.format("%,d", totalPrice),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            color = Color(128, 128, 128, 255)
                        )
                    }

                    HorizontalDivider(color = Color.LightGray)

                    Row (
                        modifier = Modifier.fillMaxWidth()
                            .padding(15.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Text(
                            text = "ผู้รับเงิน",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.weight(1f),
                        )

                        Text(
                            text = "สุวรรณี ลาทอง",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 15.sp,
                            color = Color(128, 128, 128, 255),
                            modifier = Modifier.weight(1f)
                        )

                        Spacer(modifier = Modifier.width(20.dp).weight(1f))
                    }
                }
            }
        }

        AndroidView(
            factory = {
                cardView.apply {
                    setContent {
                        cardContent()
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

private fun saveCardAsImage(view: View, context: Context) {
    view.post {
        val bitmap = Bitmap.createBitmap(
            view.width,
            view.height,
            Bitmap.Config.ARGB_8888
        )

        val canvas = android.graphics.Canvas(bitmap)
        view.draw(canvas)

        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val filename = "Receipt_$timestamp.jpg"

        var outputStream: OutputStream? = null
        var uri: Uri? = null

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val contentValues = ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                }

                val contentResolver = context.contentResolver
                uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

                uri?.let {
                    outputStream = contentResolver.openOutputStream(it)
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream!!)
                }
            } else {
                val imagesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                val image = File(imagesDir, filename)
                outputStream = FileOutputStream(image)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream as FileOutputStream)
            }

            outputStream?.flush()
            outputStream?.close()

            Toast.makeText(context, "บันทึกใบเสร็จสำเร็จ", Toast.LENGTH_SHORT).show()

        } catch (e: Exception) {
            Toast.makeText(context, "เกิดข้อผิดพลาด: ${e.message}", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }
}