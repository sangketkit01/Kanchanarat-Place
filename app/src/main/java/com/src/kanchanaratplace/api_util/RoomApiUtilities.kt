package com.src.kanchanaratplace.api_util

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.JsonObject
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
import java.util.Date

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
                          contractPath : MultipartBody.Part,slipPath: MultipartBody.Part,
                          onResponse: (ResponseBody) -> Unit, onElse: (Response<ResponseBody>) -> Unit,
                          onFailure: (Throwable) -> Unit) {
    val expireAt = LocalDateTime.now().plusMonths(6).format(DateTimeFormatter
        .ofPattern("yyyy-MM-dd HH:mm:ss")).toRequestBody()

    RoomClient.instance.insertContract(
        roomId.toRequestBody(),
        reservationId.toRequestBody(),
        contractDetail.toRequestBody(),
        contractLength.toRequestBody(),
        contractPath,
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

fun getRoomContractUtility(roomId: Int, onResponse: (Contract) -> Unit,
                           onElse: (Response<Contract>) -> Unit, onFailure: (Throwable) -> Unit) {
    RoomClient.instance.getRoomContract(roomId).enqueueCallback(onResponse, onElse, onFailure)
}

fun insertRepairUtility(
    roomId : Int, repairTitle : String, repairDetail : String,
    onResponse: (JsonObject) -> Unit, onElse: (Response<JsonObject>) -> Unit,
    onFailure: (Throwable) -> Unit
){
    RoomClient.instance.insertRepair(roomId,repairTitle,repairDetail)
        .enqueueCallback(onResponse,onElse, onFailure)
}

fun insertRepairImagesUtility(
    repairId : Int, imagePath : MultipartBody.Part, onResponse: (ResponseBody) -> Unit,
    onElse: (Response<ResponseBody>) -> Unit, onFailure: (Throwable) -> Unit
){
    RoomClient.instance.insertRepairImages(repairId,imagePath)
        .enqueueCallback(onResponse, onElse, onFailure)
}

fun insertRepairDetailUtility(
    repairId: Int, repairDetailDetail : String, repairDetailCostDetail : String,
    onResponse: (ResponseBody) -> Unit, onElse: (Response<ResponseBody>) -> Unit,
    onFailure: (Throwable) -> Unit
){
    RoomClient.instance.insertRepairDetail(repairId,repairDetailDetail,repairDetailCostDetail)
        .enqueueCallback(onResponse, onElse, onFailure)
}

fun insertRepairDetailImageUtility(
    repairId: Int, repairDetailImagePath : MultipartBody.Part,
    onResponse: (ResponseBody) -> Unit, onElse: (Response<ResponseBody>) -> Unit,
    onFailure: (Throwable) -> Unit
){
    RoomClient.instance.insertRepairDetailImage(repairId,repairDetailImagePath)
        .enqueueCallback(onResponse, onElse, onFailure)
}

fun getUnapprovedRepairUtility(
    onResponse: (List<Repair>) -> Unit, onElse: (Response<List<Repair>>)-> Unit,
    onFailure: (Throwable) -> Unit
){
    RoomClient.instance.getUnapprovedRepair().enqueueCallback(onResponse, onElse, onFailure)
}

fun getApprovedRepairUtility(
    onResponse: (List<Repair>) -> Unit, onElse: (Response<List<Repair>>) -> Unit,
    onFailure: (Throwable) -> Unit
){
    RoomClient.instance.getApprovedRepair().enqueueCallback(onResponse, onElse, onFailure)
}

fun getSuccessRepairUtility(
    onResponse: (List<Repair>) -> Unit, onElse: (Response<List<Repair>>) -> Unit,
    onFailure: (Throwable) -> Unit
){
    RoomClient.instance.getSuccessRepair().enqueueCallback(onResponse, onElse, onFailure)
}

fun getRepairImages(
    repairId : Int, onResponse: (List<RepairImages>) -> Unit, onElse: (Response<List<RepairImages>>) -> Unit,
    onFailure: (Throwable) -> Unit
){
    RoomClient.instance.getRepairImage(repairId).enqueueCallback(onResponse,onElse,onFailure)
}

fun approveRepairUtility(
    repairId: Int , onResponse: (ResponseBody) -> Unit , onElse: (Response<ResponseBody>) -> Unit,
    onFailure: (Throwable) -> Unit
){
    RoomClient.instance.approveRepair(repairId).enqueueCallback(onResponse, onElse, onFailure)
}

fun updateRepairCostStatusUtility(
    repairId: Int, repairCost : Int, statusId : Int,
    onResponse: (ResponseBody) -> Unit, onElse: (Response<ResponseBody>) -> Unit,
    onFailure: (Throwable) -> Unit
){
    RoomClient.instance.updateRepairCostStatus(repairId,repairCost, statusId)
        .enqueueCallback(onResponse, onElse, onFailure)
}

fun getRepairDetailUtility(
    repairId: Int, onResponse: (RepairDetail) -> Unit,
    onElse: (Response<RepairDetail>) -> Unit, onFailure: (Throwable) -> Unit
){
    RoomClient.instance.getRepairDetail(repairId).enqueueCallback(onResponse, onElse, onFailure)
}

fun getRepairDetailImageUtility(
    repairId: Int, onResponse: (List<RepairDetailImage>) -> Unit,
    onElse: (Response<List<RepairDetailImage>>) -> Unit, onFailure: (Throwable) -> Unit
){
    RoomClient.instance.getRepairDetailImages(repairId).enqueueCallback(onResponse, onElse, onFailure)
}

fun insertLeavingUtility(
    roomId: Int, reportDate : String, moveOutDate : String, reason : String,
    otherDetail : String, onResponse: (ResponseBody) -> Unit,
    onElse: (Response<ResponseBody>) -> Unit, onFailure: (Throwable) -> Unit
){
    RoomClient.instance.insertLeaving(roomId, reportDate, moveOutDate, reason, otherDetail)
        .enqueueCallback(onResponse, onElse, onFailure)
}

fun getUnapprovedLeavingUtility(
    onResponse: (List<Leaving>) -> Unit, onElse: (Response<List<Leaving>>) -> Unit,
    onFailure: (Throwable) -> Unit
){
    RoomClient.instance.getUnapprovedLeaving().enqueueCallback(onResponse, onElse, onFailure)
}

fun getMemberByRoomUtility(
    roomId: Int, onResponse: (Member) -> Unit,
    onElse: (Response<Member>) -> Unit, onFailure: (Throwable) -> Unit
){
    RoomClient.instance.getMemberByRoom(roomId).enqueueCallback(onResponse, onElse, onFailure)
}

fun approveLeavingUtility(
    leavingId : Int, onResponse: (ResponseBody) -> Unit,
    onElse: (Response<ResponseBody>) -> Unit, onFailure: (Throwable) -> Unit
){
    RoomClient.instance.approveLeaving(leavingId).enqueueCallback(onResponse, onElse, onFailure)
}

fun rejectLeavingUtility(
    leavingId: Int, rejectReason : String, onResponse: (ResponseBody) -> Unit,
    onElse: (Response<ResponseBody>) -> Unit, onFailure: (Throwable) -> Unit
){
    RoomClient.instance.rejectLeaving(leavingId,rejectReason).enqueueCallback(onResponse, onElse, onFailure)
}

fun updateProfileUtility(
    memberId : Int , name : String , email : String , phone : String , birth : String,
    imagePath: MultipartBody.Part?, onResponse: (ResponseBody) -> Unit
    , onElse: (Response<ResponseBody>) -> Unit, onFailure: (Throwable) -> Unit
){
    val nameBody = name.toRequestBody()
    val emailBody = email.toRequestBody()
    val phoneBody = phone.toRequestBody()
    val birthBody = birth.toRequestBody()
    RoomClient.instance.updateProfile(memberId,nameBody,emailBody,phoneBody,birthBody,imagePath)
        .enqueueCallback(onResponse, onElse, onFailure)
}

private fun String.toRequestBody() = this.toRequestBody("text/plain".toMediaTypeOrNull())
private fun Int.toRequestBody() = this.toString().toRequestBody("text/plain".toMediaTypeOrNull())
