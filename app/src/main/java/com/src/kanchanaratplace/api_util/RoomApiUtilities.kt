package com.src.kanchanaratplace.api_util

import android.os.Build
import androidx.annotation.RequiresApi
import com.src.kanchanaratplace.api.RoomAPI
import com.src.kanchanaratplace.data.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object RoomClient {
    val instance: RoomAPI by lazy { RoomAPI.create() }
}

fun <T> Call<T>.enqueueCallback(
    onResponse: (T) -> Unit,
    onElse: (Response<T>) -> Unit,
    onFailure: (Throwable) -> Unit
) {
    this.enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            response.body()?.let(onResponse) ?: onElse(response)
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            onFailure(t)
        }
    })
}

fun getRoomUtility(floor: Int, onResponse: (List<Rooms>) -> Unit, onElse: (Response<List<Rooms>>) -> Unit,
                   onFailure: (Throwable) -> Unit) {
    RoomClient.instance.getRoom(floor).enqueueCallback(onResponse, onElse, onFailure)
}

fun getOneRoomUtility(roomId: Int, onResponse: (DefaultRooms) -> Unit, onElse: (Response<DefaultRooms>) -> Unit,
                      onFailure: (Throwable) -> Unit) {
    RoomClient.instance.getOneRoom(roomId).enqueueCallback(onResponse, onElse, onFailure)
}

fun makeRoomUnavailableUtility(roomId: Int, onResponse: (ResponseBody) -> Unit,
                               onElse: (Response<ResponseBody>) -> Unit, onFailure: (Throwable) -> Unit){
    RoomClient.instance.makeRoomUnavailable(roomId).enqueueCallback(onResponse,onElse,onFailure)
}

fun reservingRoomUtility(roomId: Int, statusId: Int, name: String, phone: String, email: String, line: String,
                         slipPath: MultipartBody.Part, onResponse: (ResponseBody) -> Unit,
                         onElse: (Response<ResponseBody>) -> Unit, onFailure: (Throwable) -> Unit) {
    RoomClient.instance.reservingRoom(
        roomId,
        statusId.toRequestBody(),
        name.toRequestBody(),
        phone.toRequestBody(),
        email.toRequestBody(),
        line.toRequestBody(),
        slipPath
    ).enqueueCallback(onResponse, onElse, onFailure)
}

fun checkReservationUtility(name: String, phone: String, onResponse: (Reservation) -> Unit,
                            onElse: (Response<Reservation>) -> Unit, onFailure: (Throwable) -> Unit) {
    RoomClient.instance.checkReservation(name, phone).enqueueCallback(onResponse, onElse, onFailure)
}

fun getReservationUtility(reservationId: Int, onResponse: (Reservation) -> Unit,
                          onElse: (Response<Reservation>) -> Unit, onFailure: (Throwable) -> Unit) {
    RoomClient.instance.getReservation(reservationId).enqueueCallback(onResponse, onElse, onFailure)
}

fun getAllReservedUtility(onResponse: (List<Reservation>) -> Unit, onElse: (Response<List<Reservation>>) -> Unit,
                          onFailure: (Throwable) -> Unit) {
    RoomClient.instance.getAllReserved().enqueueCallback(onResponse, onElse, onFailure)
}

fun approveReservationUtility(reservationId: Int, onResponse: (ResponseBody) -> Unit,
                              onElse: (Response<ResponseBody>) -> Unit, onFailure: (Throwable) -> Unit) {
    RoomClient.instance.approveReservation(reservationId).enqueueCallback(onResponse, onElse, onFailure)
}

@RequiresApi(Build.VERSION_CODES.O)
fun insertContractUtility(roomId: Int,reservationId: Int, contractDetail: String, contractLength: Int,
                          slipPath: MultipartBody.Part,
                          onResponse: (ResponseBody) -> Unit, onElse: (Response<ResponseBody>) -> Unit,
                          onFailure: (Throwable) -> Unit) {
    val expireAt = LocalDateTime.now().plusMonths(6).format(DateTimeFormatter
        .ofPattern("yyyy-MM-dd HH:mm:ss")).toRequestBody()

    RoomClient.instance.insertContract(
        roomId.toRequestBody(),
        reservationId.toRequestBody(),
        contractDetail.toRequestBody(),
        contractLength.toRequestBody(),
        slipPath,
        expireAt
    ).enqueueCallback(onResponse, onElse, onFailure)
}

fun getNewContractsUtility(onResponse: (List<Contract>) -> Unit, onElse: (Response<List<Contract>>) -> Unit,
                           onFailure: (Throwable) -> Unit){
    RoomClient.instance.getNewContracts().enqueueCallback(onResponse, onElse, onFailure)
}

fun approveContractUtility(contractId : Int, onResponse: (ResponseBody) -> Unit,
                           onElse: (Response<ResponseBody>) -> Unit, onFailure: (Throwable) -> Unit){
    RoomClient.instance.approveContract(contractId).enqueueCallback(onResponse,onElse,onFailure)
}

private fun String.toRequestBody() = this.toRequestBody("text/plain".toMediaTypeOrNull())
private fun Int.toRequestBody() = this.toString().toRequestBody("text/plain".toMediaTypeOrNull())
