package com.src.kanchanaratplace.screen.member

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.src.kanchanaratplace.api_util.insertRepairImagesUtility
import com.src.kanchanaratplace.api_util.insertRepairUtility
import com.src.kanchanaratplace.component.SampleScaffold
import com.src.kanchanaratplace.session.MemberSharePreferencesManager
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.util.UUID

@Composable
fun RepairFormScaffold(navController: NavHostController){
    SampleScaffold(navController,"แจ้งซ่อม") {
        RepairFormScreen(navController)
    }
}

@Composable
fun RepairFormScreen(navController: NavHostController) {
    var title by remember { mutableStateOf("") }
    var detail by remember { mutableStateOf("") }

    val context = LocalContext.current
    var sharePreferences = remember { MemberSharePreferencesManager(context) }

    var selectedImageUris by remember { mutableStateOf<List<Uri>>(emptyList()) }

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
                    text = "แบบฟอร์มการแจ้งซ่อม",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { newValue ->
                        title = newValue
                    },
                    label = { Text("เรื่องทีต้องการแจ้ง") },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White,
                        unfocusedIndicatorColor = Color.LightGray,
                        focusedIndicatorColor = Color.LightGray
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = detail,
                    onValueChange = { newValue ->
                        detail = newValue
                    },
                    label = { Text("รายละเอียดเพิ่มเติม (เว้นว่างได้)") },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White,
                        unfocusedIndicatorColor = Color.LightGray,
                        focusedIndicatorColor = Color.LightGray
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    text = "อัปโหลดรูปภาพ",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(10.dp))

                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 100.dp),
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.height(250.dp)
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

                TextButton(
                    onClick = {
                        insertRepairUtility(
                            sharePreferences.member?.roomId!!,
                            title,
                            detail,
                            onResponse = { response->
                                val repairId = response.get("repairId").asInt
                                selectedImageUris.forEach { uri->
                                    val path = context.getImagePart(uri,"image_path")

                                    insertRepairImagesUtility(
                                        repairId,
                                        path,
                                        onResponse = {
                                            Toast.makeText(context,"Insert repair successfully",
                                                Toast.LENGTH_SHORT).show()

                                            if(uri == selectedImageUris[selectedImageUris.size - 1]){
                                                navController.popBackStack()
                                            }
                                        },
                                        onElse = { elseResponse->
                                            Toast.makeText(context,"Insert image failed",Toast.LENGTH_SHORT)
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
                            },
                            onFailure = { t->
                                Toast.makeText(context,"Error onFailure",Toast.LENGTH_SHORT)
                                    .show()
                                Log.e("Error",t.message ?: "No Message")
                            },
                            onElse = { elseResponse->
                                Toast.makeText(context,"Insert repair failed",Toast.LENGTH_SHORT)
                                    .show()
                                Log.e("Error",elseResponse.message())
                            }
                        )
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = Color(94, 144, 255)
                    ),
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    enabled = title.isNotEmpty()
                ) {
                    Text(
                        text = "ส่ง",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}