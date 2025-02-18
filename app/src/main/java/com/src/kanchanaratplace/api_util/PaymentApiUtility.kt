package com.src.kanchanaratplace.api_util

import com.src.kanchanaratplace.api.PaymentAPI
import com.src.kanchanaratplace.api.RoomAPI
import com.src.kanchanaratplace.data.Bill
import com.src.kanchanaratplace.data.BillRequest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
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

fun getBill(
    roomId : Int, month : Int, year : Int, onResponse: (Bill) -> Unit,
    onElse: (Response<Bill>) -> Unit, onFailure: (Throwable) -> Unit
){
    PaymentClient.instance.getBill(roomId,month,year).enqueueCallback(onResponse,onElse,onFailure)
}

private fun String.toRequestBody() = this.toRequestBody("text/plain".toMediaTypeOrNull())
private fun Int.toRequestBody() = this.toString().toRequestBody("text/plain".toMediaTypeOrNull())