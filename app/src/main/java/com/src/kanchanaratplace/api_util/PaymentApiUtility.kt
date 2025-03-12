package com.src.kanchanaratplace.api_util

import com.src.kanchanaratplace.api.PaymentAPI
import com.src.kanchanaratplace.api.RoomAPI
import com.src.kanchanaratplace.data.Bill
import com.src.kanchanaratplace.data.BillRequest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.sql.Date

object PaymentClient {
    val instance: PaymentAPI by lazy { PaymentAPI.create() }
}

fun createBillUtility(
    bill : BillRequest,  onResponse : (ResponseBody) -> Unit,
    onElse : (Response<ResponseBody>) -> Unit , onFailure : (Throwable) -> Unit
){
    PaymentClient.instance.createBill(bill).enqueueCallback(onResponse,onElse, onFailure)
}

fun createWaterBillUtility(
    roomId: Int, previousWaterUsed : Int, currentWaterUsed : Int,
    waterUsed : Int, date : String, onResponse: (ResponseBody) -> Unit,
    onElse: (Response<ResponseBody>) -> Unit, onFailure: (Throwable) -> Unit
){
    PaymentClient.instance.createWaterBill(roomId, previousWaterUsed, currentWaterUsed, waterUsed, date)
        .enqueueCallback(onResponse, onElse, onFailure)
}

fun createElectricityBillUtility(
    roomId: Int, previousElectricityUsed : Int, currentElectricityUsed : Int,
    electricityUsed : Int, date : String, onResponse: (ResponseBody) -> Unit,
    onElse: (Response<ResponseBody>) -> Unit, onFailure: (Throwable) -> Unit
){
    PaymentClient.instance.createElectricityBill(roomId,previousElectricityUsed,currentElectricityUsed,
        electricityUsed,date).enqueueCallback(onResponse, onElse, onFailure)
}

fun releaseBillUtility(
    month : Int, year : Int, onResponse: (ResponseBody) -> Unit,
    onElse: (Response<ResponseBody>) -> Unit, onFailure: (Throwable) -> Unit
){
    PaymentClient.instance.releaseBill(month, year).enqueueCallback(onResponse, onElse, onFailure)
}

fun getBillUtility(
    roomId : Int, month : Int, year : Int, onResponse: (Bill) -> Unit,
    onElse: (Response<Bill>) -> Unit, onFailure: (Throwable) -> Unit
){
    PaymentClient.instance.getBill(roomId,month,year).enqueueCallback(onResponse,onElse,onFailure)
}

fun getLatestBillUtility(
    roomId: Int , onResponse: (Bill) -> Unit,
    onElse: (Response<Bill>) -> Unit,onFailure: (Throwable) -> Unit
){
    PaymentClient.instance.getLatestBill(roomId).enqueueCallback(onResponse,onElse,onFailure)
}

fun getRoomBillsUtility(
    roomId : Int, onResponse: (List<Bill>) -> Unit,
    onElse: (Response<List<Bill>>) -> Unit, onFailure: (Throwable) -> Unit
){
    PaymentClient.instance.getRoomBills(roomId).enqueueCallback(onResponse,onElse,onFailure)
}

fun payBillUtility(
    billId : Int, slipPath : MultipartBody.Part,
    onResponse: (ResponseBody) -> Unit, onElse: (Response<ResponseBody>) -> Unit,
    onFailure: (Throwable) -> Unit
){
    PaymentClient.instance.payBill(billId,slipPath).enqueueCallback(onResponse,onElse,onFailure)
}

fun approveBillUtility(
    billId: Int, onResponse: (ResponseBody) -> Unit,
    onElse: (Response<ResponseBody>) -> Unit, onFailure: (Throwable) -> Unit
){
    PaymentClient.instance.approveBill(billId).enqueueCallback(onResponse, onElse, onFailure)
}

private fun String.toRequestBody() = this.toRequestBody("text/plain".toMediaTypeOrNull())
private fun Int.toRequestBody() = this.toString().toRequestBody("text/plain".toMediaTypeOrNull())