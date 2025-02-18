package com.src.kanchanaratplace.api

import com.src.kanchanaratplace.data.Bill
import com.src.kanchanaratplace.data.BillRequest
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface PaymentAPI {

    @POST("create-bill")
    fun createBill(
        @Body bill : BillRequest
    ) : Call<ResponseBody>

    @GET("get-bill/{room_id}/{month}/{year}")
    fun getBill(
        @Path("room_id") roomId : Int,
        @Path("month") month : Int,
        @Path("year") year : Int
    ) : Call<Bill>

    companion object{
        fun create() : PaymentAPI{
            val paymentClient : PaymentAPI = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PaymentAPI::class.java)

            return paymentClient
        }
    }
}